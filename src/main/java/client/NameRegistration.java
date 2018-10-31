package client;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class NameRegistration {
    Pane pane;
    Client client;
    Label enterName;
    TextField nameField;
    ImageView imageView;
    Image allright;
    Image trouble;
    Button accept;
    ClientGUI clientGUI;

    private void nameLabelGeneration() {
        enterName = new Label("Введите имя");
        enterName.setLayoutX(300);
        enterName.setLayoutY(150);
        enterName.setFont(Font.font(24));
        enterName.setBackground(new Background(new BackgroundFill(Color.AQUA, new CornerRadii(15), new Insets(-5))));
        enterName.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(15), new BorderWidths(5), new Insets(-6))));

        pane.getChildren().add(enterName);
    }

    private void nameFieldGeneration() {
        nameField = new TextField();
        nameField.setLayoutX(300);
        nameField.setLayoutY(190);
        nameField.setMaxWidth(100);

        pane.getChildren().add(nameField);

    }

    private void imageGeneration() {
        imageView = new ImageView(trouble);
        imageView.setFitWidth(30);
        imageView.setFitHeight(30);
        imageView.setLayoutY(188);
        imageView.setLayoutX(420);

        pane.getChildren().add(imageView);

    }

    private void buttonGeneration() {
        accept = new Button();
        accept.setLayoutX(400);
        accept.setLayoutY(220);
        accept.setText("Accept");

        pane.getChildren().add(accept);
    }

    public NameRegistration(Pane pane, Client client,ClientGUI clientGUI) throws FileNotFoundException {
        this.pane = pane;
        this.client = client;
        this.clientGUI =clientGUI;
        allright = new Image(new FileInputStream("src/main/recources/thumb-up.png"));
        trouble = new Image(new FileInputStream("src/main/recources/thumb-down.png"));
        nameLabelGeneration();
        nameFieldGeneration();
        imageGeneration();
        buttonGeneration();
        MyChangeListener myChangeListener = new MyChangeListener(client, imageView, allright, trouble);
        nameField.textProperty().addListener(myChangeListener);
        accept.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!client.isExist(nameField.getText())) {
                    client.addName(nameField.getText());
                    hide();
                    clientGUI.addCommunication();
                }
            }
        });

    }

    private void hide() {
        pane.getChildren().removeAll(enterName, nameField, imageView, accept);
    }
}
