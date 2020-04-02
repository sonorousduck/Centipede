package Centipede;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.Dictionary;
import java.util.Objects;

public class CentipedeBody extends ImageView {
    private Settings setup = new Settings();
    private Dictionary<String, Integer> settings = setup.createDictionary();
    private int movement = 2;



    CentipedeBody(Image image) {
        super(image);
    }

    /**
     * TODO: Turn into a mushroom. Right now, just a Ship.
     */
    public Image becomeMushroom() {
        return new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("Mushroom.png")).toString(), true);
    }

    public void move() {
        this.setX(this.getX() + movement);

        if (this.getX() > settings.get("width") - 15) {
            this.setY(this.getY() + 20);
            movement *= -1;
        } else if (this.getX() <= 0) {
            this.setY(this.getY() + 20);
            movement *= -1;
        }

//        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(16), e -> {
//            this.setX(this.getX() + movement);
//            this.setY(this.getY());
//        }));
//
//        timeline.setCycleCount(Timeline.INDEFINITE);
//        timeline.play();
    }





}
