package Snake;

import javafx.geometry.Point2D;

public class Body {
    private Point2D location;

    Body(Point2D location) {
        this.location = location;
    }

    public Point2D getLocation() {
        return this.location;
    }

    public void setLocation(Point2D location) {
        this.location = location;
    }

}
