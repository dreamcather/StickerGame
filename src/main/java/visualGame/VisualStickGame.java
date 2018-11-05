package visualGame;

import client.Client;
import game.GameConverter;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class VisualStickGame implements EventHandler<MouseEvent> {
    private int xStart;
    private int yStart;
    private int fieldWidth;
    private int pointLength;
    private int stickLength;
    private PointModel[] pointArray;
    private PointModel[] ownerArray;
    private EdgeModel[] edgesArray;
    private GameConverter gameConverter;
    private int firstClickedPoint = -1;
    private int secondClickedPoint = -1;
    private Client client;
    private int curentEdgeCount;

    public VisualStickGame(int xStart, int yStart, int width, int height, int squareCount, Pane anchorPane, Client client) {
        this.xStart = xStart;
        this.yStart = yStart;
        pointLength = squareCount + 1;
        stickLength = squareCount;
        fieldWidth = width / (pointLength + 1);
        Pane anchorPane1 = anchorPane;
        gameConverter =new GameConverter(stickLength);
        int borderWidth = 5;
        GameBackground sceneBackground = new GameBackground(xStart + borderWidth, yStart + borderWidth,
                width - borderWidth, height - borderWidth, anchorPane);
        sceneBackground.show();
        GameBorder sceneBorder = new GameBorder(xStart, yStart, width, height, borderWidth, anchorPane);

        pointArray = new PointModel[pointLength * pointLength];
        for (int i = 0; i < pointLength * pointLength; i++) {
            pointArray[i] = new PointModel(getPointCoordinate(i), (int) fieldWidth / 5, anchorPane, i);
        }

        ownerArray = new PointModel[stickLength*stickLength];
        for (int i = 0; i < stickLength * stickLength; i++) {
            ownerArray[i] = new PointModel(getShiftPointCoordinate(i), (int) fieldWidth / 10, anchorPane, i);
            //ownerArray[i].hide();
        }
        edgesArray = new EdgeModel[2 * stickLength * pointLength];
        for (int i = 0; i < 2 * stickLength * pointLength; i++) {
            edgesArray[i] = new EdgeModel(pointArray[gameConverter.getStart(i)].getCenter(),
                    pointArray[gameConverter.getEnd(i)].getCenter(), fieldWidth / 10, anchorPane);
        }
        this.client = client;
        curentEdgeCount = 0;
    }

    private Point2D getPointCoordinate(int number) {
        int row = number / pointLength;
        int column = number % pointLength;
        int x = xStart + (column + 1) * fieldWidth;
        int y = yStart + (row + 1) * fieldWidth;
        return new Point2D(x, y);
    }

    private Point2D getShiftPointCoordinate(int number) {
        int row = (int)Math.ceil(number/stickLength);
        int column = number%stickLength;

        int parent = row*pointLength+column;

        Point2D parentPoint = getPointCoordinate(parent);
        return new Point2D(parentPoint.getX()+fieldWidth/2,parentPoint.getY()+fieldWidth/2);
    }

    public void addEdge(int first, int second) {
        int stickNumber = gameConverter.getStickNumber(first, second);
        edgesArray[stickNumber].show();
    }

    private void findClickedPoint(Point2D point) {
        int res;
        for (int j = 0; j < pointLength * pointLength; j++)
            if (pointArray[j].isNear(point)) {
                res = pointArray[j].getNumber();
                if (firstClickedPoint == -1) {
                    firstClickedPoint = res;
                    active(res);
                }
                if (firstClickedPoint == res) {
                    firstClickedPoint = -1;
                    disactive(res);
                }
                if (secondClickedPoint == -1) {
                    if (gameConverter.getStickNumber(firstClickedPoint, res) != -1) {
                        secondClickedPoint = res;
                        active(res);
                        if (client.addStick(firstClickedPoint, secondClickedPoint)) {
                            curentEdgeCount++;

                        }
                        disactive(firstClickedPoint);
                        disactive(secondClickedPoint);
                        firstClickedPoint = -1;
                        secondClickedPoint = -1;
                    }
                }
            }
    }

    private void active(int number) {
        pointArray[number].active();
    }

    private void disactive(int number) {
        pointArray[number].disactive();
    }

    public void handle(MouseEvent event) {
        Point2D point2D = new Point2D(event.getSceneX(), event.getSceneY());
        findClickedPoint(point2D);
    }

    public void addOwner(int number, boolean b) {
        ownerArray[number].show();
        if(b){
            ownerArray[number].Me();
        }
        else {
            ownerArray[number].NotMe();
        }
    }
}
