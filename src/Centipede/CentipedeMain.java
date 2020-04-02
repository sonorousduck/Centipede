package Centipede;


import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Objects;


public class CentipedeMain extends Application {



    public static void main(String[] args) {
        launch(args);
    }
    private Settings setup = new Settings();
    private Dictionary<String, Integer> settings = setup.createDictionary();
    private boolean isPlaying = true;
    private AnimationTimer animationTimer;
    private Timeline timeline;
    private ArrayList<Timeline> timelines = new ArrayList<>();
    private ArrayList<CentipedeBody> centipedeBody;
    private int length;
    private Centipede centipedeCreator = new Centipede();


    @Override
    public void start(Stage primaryStage) {

        Game game = new Game(settings.get("height"), settings.get("width"), settings.get("playerSpeed"));
        Player player = new Player(settings.get("width"), settings.get("playerSize"), settings.get("height"));



        BorderPane borderPane = new BorderPane();
        Pane pane = new Pane();
        HBox hBox = new HBox();
        hBox.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));


        Text text = new Text("Score: ");
        text.setFill(Color.WHITE);
        text.setStyle("-fx-font-weight: bold");
        text.setFont(Font.font("Arial", 20));

        hBox.getChildren().addAll(text);



        pane.getChildren().add(player.getPlayer());


        borderPane.setCenter(pane);
        borderPane.setTop(hBox);


        Centipede centipede = new Centipede();
        centipedeBody = centipede.createCentipede(5);
        length = centipedeBody.size();




        pane.getChildren().addAll(centipedeBody);


        Scene scene = new Scene(borderPane, settings.get("width"), settings.get("height"));
        scene.setFill(Color.BLACK);




        if (isPlaying) {
            animationTimer = playAnimation(game, player);
            animationTimer.start();
            movementControl(scene, game, player, pane);
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
                    final double oldX = player.getPlayer().getTranslateX();
                    final double newX = Math.max(-game.getWidth() / 2, Math.min(game.getWidth(), oldX + deltaX));
                    player.getPlayer().setTranslateX(newX);
                    player.setX(newX + settings.get("width")/2);




//                    try {
//
//
//                        for (int i = 1; i < length; i++) {
//                            int j = i;
//                            //System.out.println(j);
//                            System.out.println(centipedeBody.size());
//
////                                centipedeBody.get(increment).setX(centipedeBody.get(increment - 1).getX());
////                                centipedeBody.get(increment).setY(centipedeBody.get(increment - 1).getY());
//                                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(16), e -> {
//                                    centipedeBody.get(j).setX(centipedeBody.get(j - 1).getX() - 20);
//                                    centipedeBody.get(j).setY(centipedeBody.get(j - 1).getY());
//                                }));
//                                timeline.setCycleCount(Timeline.INDEFINITE);
//                                timeline.play();
//                        }
//                        centipedeBody.get(0).setX(centipedeBody.get(0).getX() + 1);
//
//
//
//                    }
//                    catch (IndexOutOfBoundsException e) {
//                        System.out.println("ERROR!");
//                    }





                }
                game.setLastUpdateTime(timestamp);
            }
        };
        return animationTimer;
    }

    public void movementControl(Scene scene, Game game, Player player, Pane pane) {


        Timeline timeline1 = new Timeline(new KeyFrame(Duration.millis(16), e-> {
            for (int i = 0; i < length; i++) {
                centipedeBody.get(i).move();
            }
        }));
//
        timeline1.setCycleCount(Timeline.INDEFINITE);
        timeline1.play();

        scene.setOnMouseClicked(e -> {
            System.out.println(timelines.size());
            if (e.getButton().toString().equals("PRIMARY") && isPlaying) {

                // Memory thing. Should delete and stop animations if too many of them
                if (timelines.size() > 50) {
                    for (int i = 0; i < 30; i++) {
                        timelines.get(0).stop();
                        timelines.remove(0);
                    }
                }

                Bullet bulletClass = new Bullet();
                ImageView bullet = bulletClass.createBullet(player);
                pane.getChildren().add(bullet);


                timeline = new Timeline(new KeyFrame(Duration.millis(16), ae -> {

                    bullet.setY(bullet.getY() - settings.get("bulletSpeed"));

                    for (ImageView i : centipedeBody) {

                        if (bullet.getBoundsInParent().intersects(i.getBoundsInParent()) ) {
                            if (centipedeBody.get(0) == i) {

                                //i.setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("cannonball.png")).toString()));
                                centipedeBody.get(0).setImage(centipedeBody.get(0).becomeMushroom());
                                System.out.println("You are the head");

                            } else {


                            }
                            pane.getChildren().remove(bullet);
                            i.setImage(centipedeBody.get(0).becomeMushroom());


                        }
                    }



                }));


                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.play();
                timelines.add(timeline);
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
                pauseGame(animationTimer, scene);

                PauseScreen pauseScreen = new PauseScreen();
                if(pauseScreen.display()) {
                    unpauseGame(animationTimer, scene);
                }


            }


        });

        scene.setOnKeyReleased(e -> {
            game.setRectangleVelocity(0);
        });
    }

    public void pauseGame(AnimationTimer animationTimer, Scene scene) {
        animationTimer.stop();
        isPlaying = false;
        for (Timeline timeline : timelines) {
            timeline.stop();
        }
    }

    public void unpauseGame(AnimationTimer animationTimer, Scene scene) {
        isPlaying = true;
        animationTimer.start();
        for (Timeline timeline : timelines) {

            timeline.play();
        }
    }




}
