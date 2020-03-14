import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Dictionary;


public class CentipedeMain extends Application {



    public static void main(String[] args) {
        launch(args);
    }
    private Settings setup = new Settings();
    private Dictionary<String, Integer> settings = setup.createDictionary();
    private boolean isPlaying = true;









    @Override
    public void start(Stage primaryStage) {

        Game game = new Game(settings.get("height"), settings.get("width"), settings.get("playerSpeed"));
        Player player = new Player(settings.get("width"), settings.get("playerSize"), settings.get("height"));



        Pane pane = new Pane();
        System.out.println(settings);

        pane.getChildren().add(player.getRectangle());
        Scene scene = new Scene(pane, settings.get("width"), settings.get("height"));


        // Cannonball. Will change image later



        if (isPlaying) {
            playAnimation(game, player);
            movementControl(scene, game, player, pane);
        }





        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void playAnimation(Game game, Player player) {
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
        animationTimer.start();
    }

    public void movementControl(Scene scene, Game game, Player player, Pane pane) {

        scene.setOnMouseClicked(e -> {
            if (e.getButton().toString().equals("PRIMARY")) {
                Bullet bulletClass = new Bullet();
                ImageView bullet = bulletClass.createBullet(player);
                pane.getChildren().add(bullet);


                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), ae -> {

                    bullet.setY(bullet.getY() - settings.get("bulletSpeed"));

//                    bullet.setStartX(bullet.getStartX());
//                    bullet.setEndX(bullet.getEndX());
//                    bullet.setStartY(bullet.getEndY());
//                    bullet.setEndY(bullet.getEndY() - 20);

//                    if (bullet.getBoundsInParent().intersects(rect1.getBoundsInParent())) {
//                        pane.getChildren().remove(rect1);
//                        pane.getChildren().remove(bullet);
//                    }
                }));
                timeline.setCycleCount(Timeline.INDEFINITE);
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
        });

        scene.setOnKeyReleased(e -> {
            game.setRectangleVelocity(0);
        });
    }


}
