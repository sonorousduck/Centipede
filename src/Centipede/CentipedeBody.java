package Centipede;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.sql.Time;
import java.util.Dictionary;
import java.util.Objects;

public class CentipedeBody extends ImageView {
    private Settings setup = new Settings();
    private Dictionary<String, Integer> settings = setup.createDictionary();
    private int movement = 2;
    private boolean alive = true;



    CentipedeBody(Image image) {
        super(image);
    }

    /**
     * TODO: Turn into a mushroom. Right now, just a Ship.
     */

    public void move() {
        this.setX(this.getX() + movement);

        if (this.getX() > settings.get("width") - 15) {
            movement *= -1;
            this.setY(this.getY() + 20);

        } else if (this.getX() <= 0) {
            movement *= -1;
            this.setY(this.getY() + 20);

        }

    }
    public void stopMovement() {
        this.movement = 0;
    }

    public Mushroom stopMovementAndKill() {
        this.movement = 0;
        alive = false;
        Mushroom mushroom = new Mushroom(this.getX(), this.getY());
        mushroom.setPreserveRatio(true);
        mushroom.setFitHeight(15);
        this.setX(1);
        return mushroom;
    }

    public void startMovement() {
        if(alive) {
            this.movement = 2;
        }
    }

    public void flipDirections() {
        this.movement *= -1;
        this.setY(this.getY() + 15);
//        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(16), e-> {
//            this.setY(this.getY() + .15);
//        }));
//        timeline.setCycleCount(16);
//        timeline.play();

    }





}
