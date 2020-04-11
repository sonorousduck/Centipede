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
        Image testImage = new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("LaserBeam.png")).toString(), true);
        ImageView imageView = new ImageView(testImage);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(17.5);
        imageView.setX(player.getX() + player.getSize() / 1.57);
        imageView.setY(player.getY() - player.getSize() + 5);
        imageView.setSmooth(true);
        return imageView;

    }
}
