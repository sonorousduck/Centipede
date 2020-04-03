package Centipede;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Dictionary;
import java.util.Objects;

public class Mushroom extends ImageView {
    private Settings setup = new Settings();
    private Dictionary<String, Integer> settings = setup.createDictionary();
    private int timesHit = 0;
    private double locationX;
    private double locationY;

    Mushroom(double locationX, double locationY) {
        this.setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("Mushroom1.png")).toString(), true));
        this.locationX = locationX;
        this.locationY = locationY;
        this.setX(locationX);
        this.setY(locationY);
        this.setPreserveRatio(true);
        this.setFitHeight(20);
    }

    Mushroom(Image image) {
        super(image);
        this.setPreserveRatio(true);
        this.setFitHeight(20);
    }

    Mushroom(Image image, double locationX, double locationY) {
        super(image);
        this.setPreserveRatio(true);
        this.setFitHeight(20);
        this.locationX = locationX;
        this.locationY = locationY;
    }

    public void randomlyGenerateMushroomLocation() {
        this.locationX = Math.random() * (settings.get("width") - 20);
        this.locationY = Math.random() * (settings.get("height") - 200);
        this.setX(locationX);
        this.setY(locationY);
    }

    public double getLocationX() {
        return this.locationX;
    }
    public double getLocationY() {
        return this.locationY;
    }

    public void onHit() {
        this.timesHit++;
        System.out.println(timesHit);
        switch (timesHit) {
            case 0:
                break;
            case 1:
                this.setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("Mushroom2.png")).toString(), true));
                break;
            case 2:
                this.setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("Mushroom3.png")).toString(), true));
                //this.setFitHeight(8);
                break;
            case 3:
                this.setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("Mushroom4.png")).toString(), true));
                //this.setFitHeight(8);
                break;
            default:
                this.setVisible(false);
                this.setX(1000000);
        }
    }


}
