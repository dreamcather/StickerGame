package client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import server.Bridge;
import visualGame.VisualStickGame;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientGUI extends Application {
    private Stage window;
    private Scene scene;
    private Pane layout;
    private VisualStickGame visualStickGame;
    private Client client;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Game");
        layout = new Pane();
        Registry reg = LocateRegistry.getRegistry("localhost");
        Bridge bridge = (Bridge) reg.lookup(Bridge.NAME);
        client = new Client(bridge);
        scene = new Scene(layout, 750, 600);
        window.setScene(scene);
        visualStickGame = new VisualStickGame(50, 50, 300, 300, 4, layout);
        visualStickGame.show();
        scene.setOnMouseClicked(visualStickGame::handle);
        window.show();
    }
}
