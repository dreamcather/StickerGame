package client;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class MyChangeListener implements ChangeListener<String> {
    Client client;
    ImageView imageView;
    Image allright;
    Image trouble;

    public MyChangeListener(Client client, ImageView imageView, Image allright, Image trouble) {
        this.client = client;
        this.imageView = imageView;
        this.allright = allright;
        this.trouble = trouble;
    }

    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if(newValue!="") {
            if (client.isExist(newValue)) {
                imageView.setImage(trouble);
            } else {
                imageView.setImage(allright);
            }
        }else {
            imageView.setImage(trouble);
        }
    }
}
