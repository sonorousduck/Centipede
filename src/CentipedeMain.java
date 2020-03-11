import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CentipedeMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private int height = 800;
    private int width = 800;
    private int rectangleSpeed = 200;
    private double rectangleVelocity;
    private long lastUpdateTime;




    @Override
    public void start(Stage primaryStage) {

        Pane pane = new Pane();
        Player player = new Player(width, 15, height);







        Rectangle rect1 = new Rectangle();
        rect1.setWidth(600);
        rect1.setHeight(50);
        rect1.setY(200);




        pane.getChildren().addAll(player.getRectangle(), rect1);

        Scene scene = new Scene(pane, width, height);

        primaryStage.setTitle("Centipede");
        primaryStage.setScene(scene);
        primaryStage.show();


        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long timestamp) {
                if (lastUpdateTime > 0) {
                    final double elapsedSeconds = (timestamp - lastUpdateTime) / 1_000_000_000.0 ;
                    final double deltaX = elapsedSeconds * rectangleVelocity;
                    final double oldX = player.getRectangle().getTranslateX();
                    final double newX = Math.max(-width / 2, Math.min(width, oldX + deltaX));
                    player.getRectangle().setTranslateX(newX);

                }
                lastUpdateTime = timestamp;
            }
        };
        animationTimer.start();


        scene.setOnKeyPressed(e -> {

            if (e.getCode() == KeyCode.A) {


                rectangleVelocity = -rectangleSpeed;

            }

            if (e.getCode() == KeyCode.D) {


                rectangleVelocity = rectangleSpeed;

            }

            if (e.getCode() == KeyCode.SPACE) {
                System.out.println("SPACE!");
                Line line = new Line();
                line.setStartY(player.getRectangle().getY());
                line.setEndY(player.getRectangle().getY() - 10);
                line.setStartX(player.getRectangle().getTranslateX() + width / 2 + player.getSize() / 2);
                line.setEndX(player.getRectangle().getTranslateX() + width / 2 + player.getSize() / 2);
                pane.getChildren().addAll(line);

                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), ae -> {
                    line.setStartX(line.getStartX());
                    line.setEndX(line.getEndX());
                    line.setStartY(line.getEndY());
                    line.setEndY(line.getEndY() - 20);

                    if (line.getBoundsInParent().intersects(rect1.getBoundsInParent())) {
                        pane.getChildren().remove(rect1);
                        pane.getChildren().remove(line);
                    }

                }));
                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.play();
            }
        });

        scene.setOnKeyReleased(e -> {
            rectangleVelocity = 0;
        });




    }
}
