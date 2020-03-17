package Centipede;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


import java.util.Objects;

public class Player {
    private double x;
    private int y;
    private int size;
    private Rectangle rectangle;
    private ImageView player;



    Player(int width, int size, int height) {


        this.x = width / 2;
        this.size = size;
        //this.rectangle = new Rectangle();
        this.y = height - 60;



        Image ship = new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("Ship.png")).toString(), true);
        javafx.scene.image.ImageView imageView = new javafx.scene.image.ImageView(ship);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(30);
        imageView.setX(this.x);
        imageView.setY(this.y);
        imageView.setSmooth(true);
        this.player = imageView;

    }


    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public int getY() { return this.y; }

    public int getSize() {
        return this.size;
    }

    public ImageView getPlayer() {
        return this.player;
    }




}
