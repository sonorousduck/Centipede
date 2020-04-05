package Centipede;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class PauseScreen {


    private boolean closed = false;

    public boolean display() {
        Stage pauseScreen = new Stage();

        Rectangle exitGame = new Rectangle();
        exitGame.setX(50);
        exitGame.setWidth(700);
        exitGame.setY(700);
        exitGame.setHeight(75);
        exitGame.setFill(Color.TRANSPARENT);
        exitGame.setStroke(Color.BLACK);
        Text exitGameText = new Text("EXIT GAME");
        exitGameText.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        exitGameText.setX(exitGame.getX() + (exitGame.getWidth() / 2));
        exitGameText.setY(exitGame.getY() + (exitGame.getHeight() / 2));

        exitGame.setOnMouseEntered(e -> {
            exitGame.setFill(Color.LIME);
        });

        exitGameText.setOnMouseEntered(e ->{
            exitGame.setFill(Color.LIME);
        });

        exitGameText.setOnMouseExited(e-> {
            exitGame.setFill(Color.TRANSPARENT);
        });

        exitGame.setOnMouseExited(e-> {
            exitGame.setFill(Color.TRANSPARENT);
        });

        exitGame.setOnMouseClicked(e-> {
            CentipedeMain main = new CentipedeMain();
            main.start(new Stage());
            Platform.exit();


        });




        Rectangle rectangle1 = new Rectangle(50, 50, 100, 100);
        rectangle1.setOnMouseClicked(e ->{
            closed = true;
            pauseScreen.close();

        });

        Pane pane = new Pane();
        pane.getChildren().add(rectangle1);
        pane.getChildren().add(exitGame);
        pane.getChildren().add(exitGameText);
        Scene scene = new Scene(pane, 800, 800);
        pauseScreen.setScene(scene);
        pauseScreen.showAndWait();


        return closed;

    }
}
