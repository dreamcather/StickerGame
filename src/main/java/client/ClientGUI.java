package client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import server.Bridge;
import visualGame.VisualStickGame;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class  ClientGUI extends Application {
    private Stage window;
    private Scene scene;
    private Pane layout;
    private NameRegistration nameRegistration;
    private Comunication comunication;
    private VisualStickGame visualStickGame;
    private Client client;

    public void addCommunication(){
        comunication = new Comunication(layout,client);
    }

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
        //nameRegistration = new NameRegistration(layout,client,this);
        visualStickGame = new VisualStickGame(50, 50, 400, 400, 4, layout,client);
        visualStickGame.show();
        scene.setOnMouseClicked(visualStickGame::handle);
        window.show();
    }
}
