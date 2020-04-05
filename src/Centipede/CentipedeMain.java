package Centipede;


import javafx.animation.*;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Pos;
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

import java.math.BigInteger;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Objects;
import java.util.Random;


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
    private ArrayList<ArrayList<CentipedeBody>> listOfCentipedes = new ArrayList<ArrayList<CentipedeBody>>();
    private int lives = 3;
    private ArrayList<Mushroom> mushroomList = new ArrayList<>();
    private static final int MAX_LENGTH = 13;
    private Text score;
    private HBox hBox = new HBox();


    @Override
    public void start(Stage primaryStage) {

        Game game = new Game(settings.get("height"), settings.get("width"), settings.get("playerSpeed"));
        Player player = new Player(settings.get("width"), settings.get("playerSize"), settings.get("height"));


        BorderPane borderPane = new BorderPane();
        Pane pane = new Pane();
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

        Text fillerText = new Text("\t\t\t\t\t\t\t\t\t\t\t\tLives: ");
        fillerText.setFill(Color.WHITE);
        fillerText.setStyle("-fx-font-weight: bold");
        fillerText.setFont(Font.font("Arial", 20));




        hBox.getChildren().addAll(text, score, fillerText);

        for (int i = 0; i < lives; i++) {
            ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("Ship.png")).toString(), true));
            imageView.setPreserveRatio(true);
            imageView.setFitHeight(20);
            hBox.getChildren().add(imageView);
        }


        pane.getChildren().add(player.getPlayer());


        borderPane.setCenter(pane);
        borderPane.setTop(hBox);



        Centipede centipede = new Centipede();
        centipedeBody = centipede.createCentipede(5);
        listOfCentipedes.add(centipedeBody);


        pane.getChildren().addAll(centipedeBody);


        Scene scene = new Scene(borderPane, settings.get("width"), settings.get("height"));
        scene.setFill(Color.BLACK);
        System.out.println(scene.getWidth());

        if (isPlaying) {
            animationTimer = playAnimation(game, player);
            animationTimer.start();
            movementControl(scene, game, player, pane);


        }


        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        primaryStage.show();

    }

    public AnimationTimer playAnimation(Game game, Player player) {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long timestamp) {
                if (game.getLastUpdateTime() > 0) {
                    final double elapsedSeconds = (timestamp - game.getLastUpdateTime()) / 1_000_000_000.0;
                    final double deltaX = elapsedSeconds * game.getRectangleVelocity();
                    final double oldX = player.getPlayer().getTranslateX();
                    final double newX = Math.max(-game.getWidth() / 2, Math.min(game.getWidth(), oldX + deltaX));
                    player.getPlayer().setTranslateX(newX);
                    player.setX(newX + settings.get("width") / 2);


                }
                game.setLastUpdateTime(timestamp);
            }
        };
        return animationTimer;
    }

    public void movementControl(Scene scene, Game game, Player player, Pane pane) {


        Timeline newCentipedes = new Timeline(new KeyFrame(Duration.seconds(15), e -> {
            if (lives == 0) {
               pauseGame(animationTimer);
            }

            Centipede centipede = new Centipede();

            ArrayList<CentipedeBody> newCentipede = new ArrayList<>(centipede.createCentipede(new Random().nextInt(MAX_LENGTH - 3) + 3));
            for (CentipedeBody centipede1 : newCentipede) {
                centipede1.startMovement();
                centipede1.move(mushroomList);
                centipedeBody.add(centipede1);
                pane.getChildren().addAll(centipede1);
            }
        }));
        newCentipedes.setCycleCount(Timeline.INDEFINITE);
        newCentipedes.play();
        timelines.add(0, newCentipedes);

        Timeline timeline1 = new Timeline(new KeyFrame(Duration.millis(16), e -> {
            boolean runNext = false;
            for(int j = 0; j < listOfCentipedes.size(); j++) {
                for (int i = 0; i < listOfCentipedes.get(j).size(); i++) {
                    listOfCentipedes.get(j).get(i).move(mushroomList);
                    if (i - 1 == -1) {
                    } else if (listOfCentipedes.get(j).get(i).intersects(listOfCentipedes.get(j).get(i - 1).getBoundsInParent())) {
                        double direction = listOfCentipedes.get(j).get(i).getMovement();
                        if (direction < 1) {
                            listOfCentipedes.get(j).get(i).setX(listOfCentipedes.get(j).get(i).getX() - 5);
                        } else {
                            listOfCentipedes.get(j).get(i).setX(listOfCentipedes.get(j).get(i).getX() + 5);
                        }
                    }



                    if (listOfCentipedes.get(j).get(i).intersects(player.getPlayer().getBoundsInParent()) && !runNext) {
                        runNext = true;
                    }

                    if (runNext) {
                        System.out.println("REMOVED");
                        listOfCentipedes.get(j).get(i).stopMovementAndKill();

                    }



                }
            }
            if (runNext) {
                lives--;
                hBox.getChildren().remove(hBox.getChildren().size() - 1);
                System.out.println("Lives: " + lives);
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
                    for (int i = 1; i < 30; i++) {
                        timelines.get(1).stop();
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

                        if (bullet.getBoundsInParent().intersects(i.getBoundsInParent())) {
                            if (centipedeBody.get(0) == i) {

                                System.out.println("You are the head");

                            }

                            pane.getChildren().remove(bullet);
                            Mushroom mushroom = i.stopMovementAndKill();
                            pane.getChildren().add(mushroom);
                            i.flipDirections();
                            mushroomList.add(mushroom);
                            bullet.setX(100000);
                            pane.getChildren().remove(bullet);
                            int scoreNum = Integer.parseInt(score.getText());
                            scoreNum += 100;
                            score.setText(String.valueOf(scoreNum));
                        }
                    }
                    for (Mushroom m : mushroomList) {
                        if (bullet.getBoundsInParent().intersects(m.getBoundsInParent())) {
                            m.onHit();
                            bullet.setX(100000);
                            pane.getChildren().remove(bullet);
                        }
                    }
                }));


                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.play();
                timelines.add(timeline);
            }

        });


        //TODO: IMPLEMENT MOVING FORWARD AND BACK, ALSO MAKING IT SO YOU CAN'T GO OUT OF THE SCREEN. BOTH OF THESE WILL PLAY OFF THE OTHER, FIGURING ONE OUT LETS ME DO THE OTHER


        scene.setOnKeyPressed(e -> {

            if (e.getCode() == KeyCode.A || e.getCode() == KeyCode.LEFT) {
                game.setRectangleVelocity(-game.getRectangleSpeed());
            }
            if ((e.getCode() == KeyCode.D || e.getCode() == KeyCode.RIGHT)) {
                game.setRectangleVelocity(game.getRectangleSpeed());
            }
            if (e.getCode() == KeyCode.W || e.getCode() == KeyCode.UP) {

            }
            if (e.getCode() == KeyCode.SPACE) {
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

                        if (bullet.getBoundsInParent().intersects(i.getBoundsInParent())) {
                            if (centipedeBody.get(0) == i) {

                                System.out.println("You are the head");

                            }

                            pane.getChildren().remove(bullet);
                            Mushroom mushroom = i.stopMovementAndKill();
                            pane.getChildren().add(mushroom);
                            i.flipDirections();
                            mushroomList.add(mushroom);
                            bullet.setX(100000);
                            pane.getChildren().remove(bullet);
                            int scoreNum = Integer.parseInt(score.getText());
                            scoreNum += 100;
                            score.setText(String.valueOf(scoreNum));
                        }
                    }
                    for (Mushroom m : mushroomList) {
                        if (bullet.getBoundsInParent().intersects(m.getBoundsInParent())) {
                            m.onHit();
                            bullet.setX(100000);
                            pane.getChildren().remove(bullet);
                        }
                    }
                }));


                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.play();
                timelines.add(timeline);
            }

            if (e.getCode() == KeyCode.ESCAPE) {
                pauseGame(animationTimer);

                PauseScreen pauseScreen = new PauseScreen();
                if (pauseScreen.display()) {
                    unpauseGame(animationTimer);
                }
            }
        });

        scene.setOnKeyReleased(e -> {
            System.out.println(e.getCode());
            if (e.getCode() == KeyCode.A || e.getCode() == KeyCode.D || e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.LEFT) {
                game.setRectangleVelocity(0);
            }
        });
    }

    public void pauseGame(AnimationTimer animationTimer) {
        animationTimer.stop();
        isPlaying = false;
        for (Timeline timeline : timelines) {
            timeline.stop();
        }
        for (CentipedeBody centipedeBody : centipedeBody) {
            centipedeBody.stopMovement();
        }
    }

    public void unpauseGame(AnimationTimer animationTimer) {
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
        for (int i = 0; i < 30; i++) {
            Image image = new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("Mushroom1.png")).toString(), true);
            Mushroom mushroom = new Mushroom(image);


            mushroom.randomlyGenerateMushroomLocation();
            mushroomList.add(mushroom);
            pane.getChildren().add(mushroom);

        }
    }


}
