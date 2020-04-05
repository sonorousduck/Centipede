package Centipede;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.Dictionary;

public class CentipedeBody extends ImageView {
    private Settings setup = new Settings();
    private Dictionary<String, Integer> settings = setup.createDictionary();
    private double movement = 15;
    private boolean alive = true;
    private double tempMovement = movement;
    private boolean hitBottom = false;
    int movementDown = 25;



    CentipedeBody(Image image) {
        super(image);
    }


    public void move(ArrayList<Mushroom> mushroomList) {
        this.setX(this.getX() + movement);

        if (this.getY() > settings.get("height") - 75 ) {
            if (!hitBottom) {
                movementDown *= -1;
            }
            hitBottom = true;

        }


        if (hitBottom && this.getY() < settings.get("height") - 150) {
            movementDown *= -1;
            hitBottom = false;
        }

        if (this.getX() > settings.get("width") - 15) {
            flipDirections();

        } else if (this.getX() <= 0) {
            flipDirections();

        } else {
            for (int i = 0; i < mushroomList.size(); i++) {
            if(this.intersects(mushroomList.get(i).getBoundsInParent())) {
                    flipDirections();
                }
            }
        }




    }
    public void stopMovement() {
        this.tempMovement = this.movement;
        this.movement = 0;
    }

    public Mushroom stopMovementAndKill() {
        this.movement = 0;
        alive = false;
        Mushroom mushroom = new Mushroom(this.getX(), this.getY());

        mushroom.setPreserveRatio(true);
        mushroom.setFitHeight(20);
        this.setX(1000000);
        return mushroom;
    }

    public void startMovement() {
        if(alive) {
            this.movement = this.tempMovement;
        }
    }

    public void flipDirections() {


        this.setRotate(this.getRotate() + 180);
        this.movement *= -1;
        this.setY(this.getY() + movementDown);

    }
    public boolean isDead() {
        return !alive;
    }

    public double getMovement() {
        return this.movement;
    }





}
