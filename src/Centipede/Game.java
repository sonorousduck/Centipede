package Centipede;

public class Game {
    private int height;
    private int width;
    private int rectangleSpeed;
    private double rectangleVelocity;
    private long lastUpdateTime;

    Game(int height, int width, int rectangleSpeed) {
        this.height = height;
        this.width = width;
        this.rectangleSpeed = rectangleSpeed;
    }

    Game() {
        this.height = 800;
        this.width = 800;
        this.rectangleSpeed = 200;
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

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

}


