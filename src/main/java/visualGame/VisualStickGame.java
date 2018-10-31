package visualGame;

import client.Client;
import client.RefreshLoop;
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
    private PointModel[] ownerMark;
    private EdgeModel[] edgesArray;
    private Pane anchorPane;
    StickGame stickGame;
    private int firstClickedPoint = -1;
    private int secondClickedPoint = -1;
    private Client client;
    private int curentEdgeCount;

    public VisualStickGame(int xStart, int yStart, int width, int height, int squareCount, Pane anchorPane,Client client) {
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
        ownerMark = new PointModel[squareCount*squareCount];
        for(int i=0;i<squareCount*squareCount;i++){
            ownerMark[i] = new PointModel(getPointCoordinateShift(i),(int)fieldWidth/10,anchorPane,pointLength*pointLength+i);
            ownerMark[i].hide();
        }
        for(int i = 0;i<pointLength*pointLength;i++){
            pointArray[i] = new PointModel(getPointCoordinate(i),(int)fieldWidth/5,anchorPane,i);
        }
        edgesArray = new EdgeModel[2*stickLength*pointLength];
        for(int i=0;i<2*stickLength*pointLength;i++){
            edgesArray[i] = new EdgeModel(pointArray[stickGame.getStart(i)].getCenter(),
                    pointArray[stickGame.getEnd(i)].getCenter(),fieldWidth/10,anchorPane);
        }
        this.client =client;
        curentEdgeCount=0;
        RefreshLoop refreshLoop = new RefreshLoop(this,client);
        refreshLoop.start();
    }

    private Point2D getPointCoordinate(int number){
        int row = number/pointLength;
        int column = number%pointLength;
        int x =xStart+(column+1)*fieldWidth;
        int y = yStart+(row+1)*fieldWidth;
        return new Point2D(x,y);
    }

    private Point2D getPointCoordinateShift(int number){
        Point2D point2D = getPointCoordinate(stickGame.getLeftUpPoint(number));
        Point2D otherPoint=new Point2D(point2D.getX()+fieldWidth/2,point2D.getY()+fieldWidth/2);
        return otherPoint;
    }

    public void addEdge(int first, int second) {
        int stickNumber = stickGame.getStickNumber(first, second);
        edgesArray[stickNumber].show();
    }
    public void addEdgeNoMY(int first, int second) {
        int stickNumber = stickGame.getStickNumber(first, second);
        stickGame.addStick(first,second,"NoMy");
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
                            if(client.turn(firstClickedPoint,secondClickedPoint)) {
                                addEdge(firstClickedPoint, secondClickedPoint);
                                curentEdgeCount++;
                            }
                            disactive(firstClickedPoint);
                            disactive(secondClickedPoint);
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
        pointArray[number].active();
    }

    public void disactive(int number) {
        pointArray[number].disactive();
    }

    public void addNoMy(int number){
        ownerMark[number].show();
        ownerMark[number].disactive();

    }
    public void addMy(int number){
        ownerMark[number].show();
        ownerMark[number].active();

    }

    @Override
    public void handle(MouseEvent event) {
        Point2D point2D = new Point2D(event.getSceneX(), event.getSceneY());
        findClickedPoint(point2D);
    }

    public void refresh(boolean[] state){
        boolean [] array = stickGame.getEdges();
        for(int i=0;i<state.length;i++){
            if((state[i])&&(!array[i])){
                int start = stickGame.getStart(i);
                int end = stickGame.getEnd(i);
                addEdge(start,end);
            }
        }
        refreshOwner();
    }

    public void refreshOwner(){
        String[] currentOwner = client.getOwner();
        for(int i=0;i<currentOwner.length;i++) {
            System.out.print(currentOwner[i]+"   ");
        }
        System.out.println();
        for(int i=0;i<currentOwner.length;i++) {
            if (currentOwner[i] != null) {
                if (currentOwner.equals(client.getName())) {
                    addMy(i);
                } else {
                    addNoMy(i);
                }
            }
        }
    }

    public StickGame getStickGame() {
        return stickGame;
    }

    public int getCurentEdgeCount(){
        return curentEdgeCount;
    }
}
