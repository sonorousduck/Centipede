package Centipede;

import Snake.Snake;
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
    private boolean closeGame = false;

    public boolean display() {
        Stage pauseScreen = new Stage();

        Rectangle exitGame = new Rectangle();
        exitGame.setX(50);
        exitGame.setWidth(700);
        exitGame.setY(700);
        exitGame.setHeight(75);
        exitGame.setFill(Color.TRANSPARENT);
        exitGame.setStroke(Color.WHITE);
        Text exitGameText = new Text("EXIT GAME");
        exitGameText.setFont(Font.font("Arial", FontWeight.BOLD, 20));


        exitGameText.setX(exitGame.getX() + (exitGame.getWidth() / 2) - 40);
        exitGameText.setY(exitGame.getY() + (exitGame.getHeight() / 2));
        exitGameText.setFill(Color.WHITE);
        exitGame.setOpacity(2);


        exitGame.setOnMouseEntered(e -> {
            exitGame.setFill(Color.LIMEGREEN);
        });

        exitGameText.setOnMouseEntered(e ->{
            exitGame.setFill(Color.LIMEGREEN);
        });

        exitGameText.setOnMouseExited(e-> {
            exitGame.setFill(Color.TRANSPARENT);
        });

        exitGame.setOnMouseExited(e-> {
            exitGame.setFill(Color.TRANSPARENT);
        });

        exitGame.setOnMouseClicked(e-> {
            closeGame = true;
            closed = true;
            pauseScreen.close();

        });


        Rectangle resumeGame = new Rectangle();
        resumeGame.setX(50);
        resumeGame.setWidth(700);
        resumeGame.setY(400);
        resumeGame.setHeight(75);
        resumeGame.setFill(Color.TRANSPARENT);
        resumeGame.setStroke(Color.WHITE);
        Text resumeGameText = new Text("RESUME");
        resumeGameText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        resumeGame.setOpacity(2);

        resumeGameText.setX(resumeGame.getX() + (resumeGame.getWidth() / 2) - 40);
        resumeGameText.setY(resumeGame.getY() + (resumeGame.getHeight() / 2));
        resumeGameText.setFill(Color.WHITE);


        resumeGame.setOnMouseEntered(e -> {

            resumeGame.setFill(Color.LIMEGREEN);
        });

        resumeGameText.setOnMouseEntered(e ->{
            resumeGame.setFill(Color.LIMEGREEN);
        });

        resumeGameText.setOnMouseExited(e-> {
            resumeGame.setFill(Color.TRANSPARENT);
        });

        resumeGame.setOnMouseExited(e-> {
            resumeGame.setFill(Color.TRANSPARENT);
        });

        resumeGame.setOnMouseClicked(e-> {
            closed = true;
            pauseScreen.close();

        });





        Pane pane = new Pane();
        pane.getChildren().add(exitGame);
        pane.getChildren().add(exitGameText);
        pane.getChildren().addAll(resumeGame, resumeGameText);
        Scene scene = new Scene(pane, 800, 800);
        scene.setFill(Color.BLACK);
        pauseScreen.setScene(scene);
        pauseScreen.showAndWait();


        return closed;
    }

    public boolean getGameClosed() {
        System.out.println("Testing");
        return closeGame;
    }
}
