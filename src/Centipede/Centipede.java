package Centipede;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class Centipede {

    public ImageView createCentipede() {
        Image centipedeHead = new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("Centipedehead.png")).toString(), true);
        ImageView imageView = new ImageView(centipedeHead);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(200);
        imageView.setX(300);
        imageView.setY(300);
        return imageView;
    }




}
