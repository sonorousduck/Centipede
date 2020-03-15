package Centipede;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;


/**
 *
 * Right now a line, soon to be replaced by some image
 */

public class Bullet {
    public ImageView createBullet(Player player) {
        Image testImage = new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("cannonball.png")).toString(), true);
        ImageView imageView = new ImageView(testImage);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(10);
        imageView.setX(player.getX());
        System.out.println(imageView.getX());
        imageView.setY(player.getY() - player.getSize());
        imageView.setSmooth(true);
        return imageView;

    }




//        Line line = new Line();
//        this.size = 10;
//
//        this.startX = player.getRectangle().getTranslateX() + game.getWidth() / 2 + player.getSize() / 2;
//        this.startY = player.getRectangle().getY();
//        this.endX = player.getRectangle().getTranslateX() + game.getWidth() / 2 + player.getSize() / 2;
//        this.endY = player.getRectangle().getY() - this.size;
//
//
//        line.setStartY(this.startY);
//        line.setEndY(this.endY);
//        line.setStartX(this.startX);
//        line.setEndX(this.endX);

    }
//
//    public double getStartX() {
//        return startX;
//    }
//
//    public void setStartX(double startX) {
//        this.startX = startX;
//    }
//
//    public double getStartY() {
//        return startY;
//    }
//
//    public void setStartY(double startY) {
//        this.startY = startY;
//    }
//
//    public double getEndX() {
//        return endX;
//    }
//
//    public void setEndX(double endX) {
//        this.endX = endX;
//    }
//
//    public double getEndY() {
//        return endY;
//    }
//
//    public void setEndY(double endY) {
//        this.endY = endY;
//    }
//
//    public double getSize() {
//        return size;
//    }
//
//    public void setSize(double size) {
//        this.size = size;
//    }

