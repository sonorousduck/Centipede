package Centipede;

import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.Objects;

public class Centipede {

    public ArrayList<CentipedeBody> createCentipede(int length) {
        ArrayList<CentipedeBody> centipedeBody = createCentipedeBody(length, new ArrayList<>());
        CentipedeBody centipedeHead = createCentipedeHead();
        centipedeBody.add(0, centipedeHead);
        return centipedeBody;

    }
    private CentipedeBody createCentipedeHead() {
        Image centipedeHead = new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("Centipedehead1.png")).toString(), true);
        CentipedeBody imageView = new CentipedeBody(centipedeHead);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(20);
        imageView.setX(300);
        imageView.setY(300);
        return imageView;
    }

    private ArrayList<CentipedeBody> createCentipedeBody(int length, ArrayList<CentipedeBody> imageViews) {
        for (int i = 0; i < length; i++) {
            Image centipedeBodyImage = new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("Centipedehead1.png")).toString(), true);
            CentipedeBody centipedeBody = new CentipedeBody(centipedeBodyImage);
            centipedeBody.setPreserveRatio(true);
            centipedeBody.setFitHeight(20);
            centipedeBody.setX(295 - (i + .75) * 17.5);
            centipedeBody.setY(300);
            imageViews.add(centipedeBody);

        }
        return imageViews;


    }




}
