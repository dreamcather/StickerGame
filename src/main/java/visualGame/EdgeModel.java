package visualGame;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;


public class EdgeModel implements VisualObject {
    private Line source;
    private Color color;

    public EdgeModel(Point2D start, Point2D end, double width, Pane pane) {
        source = new Line(start.getX(), start.getY(), end.getX(), end.getY());
        source.setStrokeWidth(width);
        source.setStroke(Color.BLACK);
        pane.getChildren().add(source);
        source.setVisible(false);
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
    }
}
