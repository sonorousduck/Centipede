package Centipede;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class EndScreen {


    public void display(Text score) throws FileNotFoundException {
        Stage endScreen = new Stage();

        Text lostGame = new Text("YOU LOST!");
        Text scoreText = new Text();

        scoreText.setText("YOUR SCORE: " + score.getText());
        lostGame.setFill(Color.WHITE);
        scoreText.setFill(Color.WHITE);
        lostGame.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        scoreText.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        try {
            FileWriter scoreTracker = new FileWriter("data/highscore.txt", true);
            scoreTracker.write(score.getText() + "\n");
            scoreTracker.close();
        } catch (IOException e) {
            System.out.println("An error has occured");
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(new File("data/highscore.txt"));

        int topScore = 0;
        while (scanner.hasNextLine()) {
            int tempInt = Integer.parseInt(scanner.nextLine());

            topScore = Math.max(tempInt, topScore);
        }

        Text highScore = new Text("TOP SCORE: " + topScore);
        highScore.setFill(Color.RED);
        highScore.setY(120);
        highScore.setFont(Font.font("Arial", FontWeight.BOLD, 20));



        lostGame.setY(30);
        scoreText.setY(75);





        Rectangle exitGame = new Rectangle();
        exitGame.setX(50);
        exitGame.setWidth(700);
        exitGame.setY(700);
        exitGame.setHeight(75);
        exitGame.setFill(Color.TRANSPARENT);
        exitGame.setStroke(Color.WHITE);
        Text exitGameText = new Text("QUIT");
        exitGameText.setFont(Font.font("Arial", FontWeight.BOLD, 20));


        exitGameText.setX(exitGame.getX() + (exitGame.getWidth() / 2) - 40);
        exitGameText.setY(exitGame.getY() + (exitGame.getHeight() / 2));
        exitGameText.setFill(Color.WHITE);
        exitGame.setOpacity(2);


        lostGame.xProperty().bind(exitGameText.xProperty());
        scoreText.xProperty().bind(exitGameText.xProperty());
        highScore.xProperty().bind(exitGameText.xProperty());

        exitGame.setOnMouseEntered(e -> {
            exitGame.setFill(Color.RED);
        });

        exitGameText.setOnMouseEntered(e ->{
            exitGame.setFill(Color.RED);
        });

        exitGameText.setOnMouseExited(e-> {
            exitGame.setFill(Color.TRANSPARENT);
        });

        exitGame.setOnMouseExited(e-> {
            exitGame.setFill(Color.TRANSPARENT);
        });

        exitGame.setOnMouseClicked(e-> {

            endScreen.close();

        });


        Rectangle playAgain = new Rectangle();
        playAgain.setX(50);
        playAgain.setWidth(700);
        playAgain.setY(400);
        playAgain.setHeight(75);
        playAgain.setFill(Color.TRANSPARENT);
        playAgain.setStroke(Color.WHITE);
        Text resumeGameText = new Text("PLAY AGAIN");
        resumeGameText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        playAgain.setOpacity(2);

        resumeGameText.setX(playAgain.getX() + (playAgain.getWidth() / 2) - 40);
        resumeGameText.setY(playAgain.getY() + (playAgain.getHeight() / 2));
        resumeGameText.setFill(Color.WHITE);


        playAgain.setOnMouseEntered(e -> {

            playAgain.setFill(Color.LIMEGREEN);
        });

        resumeGameText.setOnMouseEntered(e ->{
            playAgain.setFill(Color.LIMEGREEN);
        });

        resumeGameText.setOnMouseExited(e-> {
            playAgain.setFill(Color.TRANSPARENT);
        });

        playAgain.setOnMouseExited(e-> {
            playAgain.setFill(Color.TRANSPARENT);
        });

        playAgain.setOnMouseClicked(e-> {
            CentipedeMain centipedeMain = new CentipedeMain();
            centipedeMain.start(new Stage());
            endScreen.close();
        });







        Pane pane = new Pane();
        pane.getChildren().add(exitGame);
        pane.getChildren().add(exitGameText);
        pane.getChildren().addAll(scoreText, lostGame, highScore, playAgain, resumeGameText);
        Scene scene = new Scene(pane, 800, 800);
        scene.setFill(Color.BLACK);
        endScreen.setScene(scene);
        endScreen.show();
    }
}

