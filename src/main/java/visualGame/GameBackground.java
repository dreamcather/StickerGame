package visualGame;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameBackground implements VisualObject{
    private Rectangle source;

    public GameBackground(int xStart, int yStart, int width, int height,Pane pane) {
        source = new Rectangle(xStart,yStart,width,height);
        source.setFill(Color.YELLOW);
        pane.getChildren().add(source);
        hide();
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
