package visualGame;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class PointModel implements VisualObject {
    private Circle source;
    private boolean marked;
    private int number;

    public PointModel(Point2D point2D, int radius,Pane pane,int number) {
        source = new Circle(point2D.getX(),point2D.getY(),radius);
        pane.getChildren().add(source);
        marked = false;
        this.number = number;
    }
    public Point2D getCenter(){
        return new Point2D(source.getCenterX(),source.getCenterY());
    }

    private double distance(Point2D point)
    {
        return Math.sqrt(Math.pow(point.getX() -source.getCenterX(),2)+Math.pow(point.getY() - source.getCenterY(),2));
    }
    public boolean isNear(Point2D position){
        if(distance(position)<source.getRadius())
            return true;
        else return false;
    }

    @Override
    public void show() {
        source.setVisible(true);
    }

    @Override
    public void hide() {
        source.setVisible(false);
    }

    @Override
    public void refresh() {
        source.toFront();
    }

    public void active(){
        source.setFill(Color.RED);
        source.toFront();
    }

    public void disactive(){source.setFill(Color.BLACK);}

    public int getNumber() {
        return number;
    }
}
