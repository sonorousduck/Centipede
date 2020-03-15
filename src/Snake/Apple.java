package Snake;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Apple {
    private Color appleColor;
    private Point2D location;


    Apple(Color appleColor) {
        this.appleColor = appleColor;
        this.location = new Point2D(Math.round(Math.random() * 19), Math.round(Math.random() * 19));
    }


    public Point2D getLocation() {
        return this.location;
    }

    public void setLocation() {
        this.location = new Point2D(Math.round(Math.random() * 19), Math.round(Math.random() * 19));
    }

    public Color getAppleColor() {
        return this.appleColor;
    }



}
