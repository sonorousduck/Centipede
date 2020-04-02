package Centipede;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class CentipedeBody extends ImageView {


    CentipedeBody(Image image) {
        super(image);
    }

    /**
     * TODO: Turn into a mushroom. Right now, just a Ship.
     */
    public Image becomeMushroom() {
        return new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("LaserBeam.png")).toString(), true);
    }





}
