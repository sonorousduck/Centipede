package Centipede;

public class Game {
    private int height;
    private int width;
    private int rectangleSpeed;
    private double rectangleVelocity;
    private long lastUpdateTime;
    private double rectangleYVelocity;

    Game(int height, int width, int rectangleSpeed) {
        this.height = height;
        this.width = width;
        this.rectangleSpeed = rectangleSpeed;

    }

    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }

    public int getRectangleSpeed() {
        return rectangleSpeed;
    }

    public double getRectangleVelocity() {
        return this.rectangleVelocity;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setRectangleSpeed(int speed) {
        this.rectangleSpeed = speed;
    }

    public void setRectangleVelocity(double rectangleVelocity) {
        this.rectangleVelocity = rectangleVelocity;
    }

    public void setRectangleYVelocity(double rectangleYVelocity) { this.rectangleYVelocity = rectangleYVelocity; }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public double getRectangleYVelocity() { return this.rectangleYVelocity; }
}


