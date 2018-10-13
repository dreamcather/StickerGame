package visualGame;

import game.StickGame;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class VisualStickGame implements EventHandler<MouseEvent> {
    private int xStart;
    private int yStart;
    private int fieldWidth;
    private final int borderWidth = 5;
    private int pointLength;
    private int stickLength;
    private GameBackground sceneBackground;
    private GameBorder sceneBorder;
    private PointModel[] pointArray;
    private EdgeModel[] edgesArray;
    private Pane anchorPane;
    StickGame stickGame;
    private int firstClickedPoint = -1;
    private int secondClickedPoint = -1;

    public VisualStickGame(int xStart, int yStart, int width, int height, int squareCount, Pane anchorPane) {
        this.xStart = xStart;
        this.yStart = yStart;
        pointLength = squareCount + 1;
        stickLength = squareCount;
        fieldWidth = width / (pointLength + 1);
        stickGame = new StickGame(squareCount);
        this.anchorPane = anchorPane;
        sceneBackground = new GameBackground(xStart + borderWidth, yStart + borderWidth,
                width - borderWidth, height - borderWidth, anchorPane);
        sceneBackground.show();
        sceneBorder = new GameBorder(xStart, yStart, width, height, borderWidth, anchorPane);

        pointArray = new PointModel[pointLength*pointLength];
        for(int i = 0;i<pointLength*pointLength;i++){
            pointArray[i] = new PointModel(getPointCoordinate(i),(int)fieldWidth/5,anchorPane,i);
        }
        edgesArray = new EdgeModel[2*stickLength*pointLength];
        for(int i=0;i<2*stickLength*pointLength;i++){
            edgesArray[i] = new EdgeModel(pointArray[stickGame.getStart(i)].getCenter(),
                    pointArray[stickGame.getEnd(i)].getCenter(),fieldWidth/10,anchorPane);
        }
    }

    private Point2D getPointCoordinate(int number){
        int row = number/pointLength;
        int column = number%pointLength;
        int x =xStart+(column+1)*fieldWidth;
        int y = yStart+(row+1)*fieldWidth;
        return new Point2D(x,y);
    }

    public void addEdge(int first, int second) {
        int stickNumber = stickGame.getStickNumber(first, second);
        edgesArray[stickNumber].show();
    }

    public void show() {
    }

    public void hide() {

    }

    private int findClickedPoint(Point2D point) {
        int res = -1;
        for (int j = 0; j < pointLength * pointLength; j++)
            if (pointArray[j].isNear(point)) {
                res = pointArray[j].getNumber();
                if (firstClickedPoint == -1) {
                    firstClickedPoint = res;
                    active(res);
                    return res;
                }
                if (firstClickedPoint == res) {
                    firstClickedPoint = -1;
                    disactive(res);
                    return res;
                }
                if (secondClickedPoint == -1) {
                    if (stickGame.getStickNumber(firstClickedPoint, res) != -1) {
                        secondClickedPoint = res;
                        active(res);
                        if (stickGame.getStickNumber(firstClickedPoint, secondClickedPoint) != -1) {
                            addEdge(firstClickedPoint, secondClickedPoint);
                            firstClickedPoint = -1;
                            secondClickedPoint = -1;
                        }
                        return res;
                    }
                }
            }
        return res;
    }

    public void active(int number) {
    }

    public void disactive(int number) {
    }

    @Override
    public void handle(MouseEvent event) {
        Point2D point2D = new Point2D(event.getSceneX(), event.getSceneY());
        findClickedPoint(point2D);
    }
}
