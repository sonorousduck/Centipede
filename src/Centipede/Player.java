package Centipede;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player {
    private double x;
    private int y;
    private int size;
    private Rectangle rectangle;



    Player(int width, int size, int height) {
        this.x = width / 2;
        this.size = size;
        this.rectangle = new Rectangle();
        this.y = height - 10;

        rectangle.setHeight(this.size);
        rectangle.setWidth(this.size);

        rectangle.setX(width / 2);
        rectangle.setY(height - 20);

        rectangle.setFill(Color.RED);

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

    public Rectangle getRectangle() {
        return this.rectangle;
    }




}
