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
    private ArrayList<Mushroom> mushroomList = new ArrayList<>();
    private static final int MAX_LENGTH = 13;
    private Text score;


    @Override
    public void start(Stage primaryStage) {

        Game game = new Game(settings.get("height"), settings.get("width"), settings.get("playerSpeed"));
        Player player = new Player(settings.get("width"), settings.get("playerSize"), settings.get("height"));



        BorderPane borderPane = new BorderPane();
        Pane pane = new Pane();
        HBox hBox = new HBox();
        hBox.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        setup(pane);

        Text text = new Text("Score: ");
        text.setFill(Color.WHITE);
        text.setStyle("-fx-font-weight: bold");
        text.setFont(Font.font("Arial", 20));

        score = new Text("0");
        score.setFill(Color.WHITE);
        score.setStyle("-fx-font-weight: bold");
        score.setFont(Font.font("Arial", 20));


        hBox.getChildren().addAll(text, score);



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

        Timeline newCentipedes = new Timeline(new KeyFrame(Duration.seconds(15), e-> {
            Centipede centipede = new Centipede();
            ArrayList<CentipedeBody> newCentipede = new ArrayList<>(centipede.createCentipede((int) (Math.random() * MAX_LENGTH)));
            for (CentipedeBody centipede1 : newCentipede) {
                centipede1.startMovement();
                centipede1.move();
                centipedeBody.add(centipede1);
                pane.getChildren().addAll(centipede1);
            }
        }));
        newCentipedes.setCycleCount(Timeline.INDEFINITE);
        newCentipedes.play();

        Timeline timeline1 = new Timeline(new KeyFrame(Duration.millis(16), e-> {
            for (int i = 0; i < centipedeBody.size(); i++) {
                centipedeBody.get(i).move();

                for (CentipedeBody centipedeBody : centipedeBody) {

                    for (Mushroom mushroom : mushroomList) {
                        if (centipedeBody.intersects(mushroom.getBoundsInParent())) {
                            centipedeBody.flipDirections();
                        }
                    }
                }

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




                    for (CentipedeBody i : centipedeBody) {

                        if (i.isDead()) {
                            centipedeBody.remove(i);
                            break;
                        }

                        if (bullet.getBoundsInParent().intersects(i.getBoundsInParent()) ) {
                            if (centipedeBody.get(0) == i) {

                                //i.setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("cannonball.png")).toString()));
                                //centipedeBody.get(0).setImage(centipedeBody.get(0).becomeMushroom());
                                System.out.println("You are the head");

                            }

                            pane.getChildren().remove(bullet);
                            Mushroom mushroom = i.stopMovementAndKill();
                            pane.getChildren().add(mushroom);
                            mushroomList.add(mushroom);
                            int scoreNum = Integer.parseInt(score.getText());
                            scoreNum += 100;
                            score.setText(String.valueOf(scoreNum));





                        }
                    }
                    for (Mushroom m : mushroomList) {
                        if (bullet.getBoundsInParent().intersects(m.getBoundsInParent())) {
                            m.onHit();
                            bullet.setX(10000000);
                            pane.getChildren().remove(bullet);
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
        for (CentipedeBody centipedeBody : centipedeBody) {
            centipedeBody.stopMovement();
        }
    }

    public void unpauseGame(AnimationTimer animationTimer, Scene scene) {
        isPlaying = true;
        animationTimer.start();
        for (Timeline timeline : timelines) {

            timeline.play();
        }
        for (CentipedeBody centipedeBody : centipedeBody) {
            centipedeBody.startMovement();
        }
    }

    public void setup(Pane pane) {
        for (int i = 0; i < 30; i++ ) {
            Image image = new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("Mushroom1.png")).toString(), true);
            Mushroom mushroom = new Mushroom(image);


            mushroom.randomlyGenerateMushroomLocation();
            mushroomList.add(mushroom);
            pane.getChildren().add(mushroom);

        }
    }


}
