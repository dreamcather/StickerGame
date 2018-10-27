package client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Pair;
import server.Bridge;
import visualGame.VisualStickGame;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Optional;

public class ClientGUI extends Application {
    private Stage window;
    private Scene scene;
    private Pane layout;
    private VisualStickGame visualStickGame;
    private Client client;

    private void registartion(){
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Login in");

        // Set the button types.
        ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        TextField userName = new TextField();
        userName.setPromptText("From");
        TextField userPassword = new TextField();
        userPassword.setPromptText("To");

        gridPane.add(new Label("User Name"),0,0);
        gridPane.add(userName, 0, 1);
        gridPane.add(new Label("Password"), 0, 2);
        gridPane.add(userPassword, 0, 3);

        dialog.getDialogPane().setContent(gridPane);

        // Request focus on the username field by default.
        Platform.runLater(() -> userName.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(userName.getText(), userPassword.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(pair -> {
            System.out.println("From=" + pair.getKey() + ", To=" + pair.getValue());
        });
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
        registartion();
        //visualStickGame = new VisualStickGame(50, 50, 300, 300, 4, layout,client);
        //visualStickGame.show();
        //scene.setOnMouseClicked(visualStickGame::handle);
        window.show();
    }
}
