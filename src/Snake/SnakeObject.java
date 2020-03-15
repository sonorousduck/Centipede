package Snake;

import javafx.geometry.Point2D;

import java.util.ArrayList;

public class SnakeObject {
    private ArrayList<Body> length;
    private Point2D coordPoints;
    private Direction direction;


    SnakeObject(ArrayList<Body> length, int locationX, int locationY, Direction direction) {
        this.length = length;
        this.coordPoints = new Point2D(locationX, locationY);
        this.direction = direction;
    }


    public ArrayList<Body> getLength() {
        return length;
    }

    public void incrementLength() {
        this.length.add(new Body(new Point2D(this.length.get(this.length.size() - 1).getLocation().getX() - 1, this.length.get(this.length.size() - 1).getLocation().getY())));
    }

    public Point2D getCoordPoints() {
        return this.coordPoints;
    }

    public void setCoordPoints(double locationX, double locationY) {
        this.coordPoints = new Point2D(locationX, locationY);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return this.direction;
    }





}
