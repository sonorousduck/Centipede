package Centipede;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Objects;

public class Centipede {

    public ArrayList<ImageView> createCentipede(int length) {
        ArrayList<ImageView> centipedeBody = createCentipedeBody(length, new ArrayList<ImageView>());
        ImageView centipedeHead = createCentipedeHead();
        centipedeBody.add(0, centipedeHead);
        return centipedeBody;

    }
    private ImageView createCentipedeHead() {
        Image centipedeHead = new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("Centipedehead1.png")).toString(), true);
        ImageView imageView = new ImageView(centipedeHead);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(20);
        imageView.setX(300);
        imageView.setY(300);
        return imageView;
    }

    private ArrayList<ImageView> createCentipedeBody(int length, ArrayList<ImageView> imageViews) {
        for (int i = 0; i < length; i++) {
            Image centipedeBody = new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("Centipedehead1.png")).toString(), true);
            ImageView imageView = new ImageView(centipedeBody);
            imageView.setPreserveRatio(true);
            imageView.setFitHeight(20);
            imageView.setX(295 - (i + .75) * 17.5);
            imageView.setY(300);
            imageViews.add(imageView);

        }
        return imageViews;


    }




}
