package Centipede;


import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Dictionary;


public class CentipedeMain extends Application {



    public static void main(String[] args) {
        launch(args);
    }
    private Settings setup = new Settings();
    private Dictionary<String, Integer> settings = setup.createDictionary();
    private boolean isPlaying = true;
    private AnimationTimer animationTimer;

    @Override
    public void start(Stage primaryStage) {

        Game game = new Game(settings.get("height"), settings.get("width"), settings.get("playerSpeed"));
        Player player = new Player(settings.get("width"), settings.get("playerSize"), settings.get("height"));

        Rectangle rectangle = new Rectangle(100, 100, 100, 100);
        rectangle.setX(100);
        rectangle.setY(100);


        BorderPane borderPane = new BorderPane();
        Pane pane = new Pane();
        HBox hBox = new HBox();
        hBox.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));


        Text text = new Text("Testing");
        hBox.getChildren().addAll(text);



        pane.getChildren().add(player.getRectangle());
        pane.getChildren().add(rectangle);


        borderPane.setCenter(pane);
        borderPane.setTop(hBox);

        Scene scene = new Scene(borderPane, settings.get("width"), settings.get("height"));




        if (isPlaying) {
            animationTimer = playAnimation(game, player);
            unpauseGame(animationTimer);
            movementControl(scene, game, player, pane, rectangle);
        }





        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public AnimationTimer playAnimation(Game game, Player player) {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long timestamp) {
                // Courtesy of somewhere. Need to find it
                if (game.getLastUpdateTime() > 0) {
                    final double elapsedSeconds = (timestamp - game.getLastUpdateTime()) / 1_000_000_000.0;
                    final double deltaX = elapsedSeconds * game.getRectangleVelocity();
                    final double oldX = player.getRectangle().getTranslateX();
                    final double newX = Math.max(-game.getWidth() / 2, Math.min(game.getWidth(), oldX + deltaX));
                    player.getRectangle().setTranslateX(newX);
                    player.setX(newX + settings.get("width")/2);

                }
                game.setLastUpdateTime(timestamp);
            }
        };
        return animationTimer;
    }

    public void movementControl(Scene scene, Game game, Player player, Pane pane, Rectangle rectangle) {

        scene.setOnMouseClicked(e -> {
            if (e.getButton().toString().equals("PRIMARY")) {
                Bullet bulletClass = new Bullet();
                ImageView bullet = bulletClass.createBullet(player);
                pane.getChildren().add(bullet);



                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), ae -> {

                    bullet.setY(bullet.getY() - settings.get("bulletSpeed"));

                    if (bullet.getBoundsInParent().intersects(rectangle.getBoundsInParent())) {
                        pane.getChildren().remove(bullet);
                        pane.getChildren().remove(rectangle);
                    }
                }));

                timeline.setCycleCount(90);
                timeline.play();
            }

        });



        scene.setOnKeyPressed(e -> {

                if (e.getCode() == KeyCode.A) {
                    game.setRectangleVelocity(-game.getRectangleSpeed());
                }
                if (e.getCode() == KeyCode.D) {
                    game.setRectangleVelocity(game.getRectangleSpeed());
                }
            if (e.getCode() == KeyCode.ESCAPE) {
                pauseGame(animationTimer);



                PauseScreen pauseScreen = new PauseScreen();
                if(pauseScreen.display()) {
                    unpauseGame(animationTimer);
                }


            }


        });

        scene.setOnKeyReleased(e -> {
            game.setRectangleVelocity(0);
        });
    }

    public void pauseGame(AnimationTimer animationTimer) {
        animationTimer.stop();
    }

    public void unpauseGame(AnimationTimer animationTimer) {
        animationTimer.start();
    }



}
