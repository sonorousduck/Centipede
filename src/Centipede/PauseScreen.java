package Centipede;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PauseScreen {

    private boolean closed = false;

    public boolean display() {
        Stage pauseScreen = new Stage();

        Rectangle rectangle1 = new Rectangle(50, 50, 100, 100);
        rectangle1.setOnMouseClicked(e ->{
            closed = true;
            pauseScreen.close();

        });

        Pane pane = new Pane(rectangle1);
        Scene scene = new Scene(pane, 800, 800);
        pauseScreen.setScene(scene);
        pauseScreen.showAndWait();


        return closed;

    }
}
