package Snake;

import Main.GameMain;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.util.ArrayList;

public class Snake extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Direction direction = Direction.EAST;
    private Apple apple;
    private boolean gameOver = false;

    @Override
    public void start(Stage primaryStage) {

        GridPane gridPane = new GridPane();
        Pane pane = new Pane();
        StackPane stackPane = new StackPane();

        ArrayList<ArrayList<Rectangle>> rectangles = new ArrayList<>();
        ArrayList<ArrayList<Integer>> gameBoard = new ArrayList<>();

        Text text = new Text();
        Text instructions = new Text("Click anywhere to play again");


        instructions.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        text.setFont(Font.font(30));
        text.setText("GAME OVER!");


        text.xProperty().bind(pane.widthProperty().divide(2).subtract(75));
        text.setY(400);

        instructions.xProperty().bind(pane.widthProperty().divide(2).subtract(80));
        instructions.setY(450);
        text.setFill(Color.TRANSPARENT);
        instructions.setFill(Color.TRANSPARENT);

        pane.getChildren().addAll(text, instructions);

        stackPane.getChildren().addAll(gridPane, pane);


        Scene scene = new Scene(stackPane, 820, 820);

        for (int i = 0; i < 20; i++) {
            rectangles.add(i, new ArrayList<>());
            gameBoard.add(i, new ArrayList<>());


            for (int j = 0; j < 20; j++) {

                Rectangle rectangle = new Rectangle();
                rectangle.setWidth(800 / 20);
                rectangle.setHeight(800 / 20);
                rectangle.setFill(Color.BLACK);
                rectangle.setStroke(Color.BLACK);
                gridPane.add(rectangle, i, j);
                rectangles.get(i).add(j, rectangle);
                gameBoard.get(i).add(j, 0);

            }
        }

        SnakeObject snake = new SnakeObject(new ArrayList<>(), 1, 0, direction);
        snake.getLength().add(new Body(new Point2D(5, 1)));
        snake.incrementLength();
        snake.incrementLength();

        apple = randomAppleLocation(rectangles);


        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.W && snake.getDirection() != Direction.SOUTH) {
                snake.setDirection(Direction.NORTH);
            } else if (e.getCode() == KeyCode.D && snake.getDirection() != Direction.WEST) {
                snake.setDirection(Direction.EAST);
            } else if (e.getCode() == KeyCode.S && snake.getDirection() != Direction.NORTH) {
                snake.setDirection(Direction.SOUTH);
            } else if (e.getCode() == KeyCode.A && snake.getDirection() != Direction.EAST) {
                snake.setDirection(Direction.WEST);
            }

        });




        primaryStage.setScene(scene);
        primaryStage.show();




        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(getSpeed(snake)), e -> {
            if (isMatching(snake, apple, gameBoard, rectangles)) {


                snake.incrementLength();
                apple = randomAppleLocation(rectangles);
            }

            if (gameOver) {
                text.setFill(Color.RED);
                instructions.setFill(Color.LIMEGREEN);
                scene.setOnMouseClicked(ae -> {
                    Snake snake1 = new Snake();
                    snake1.start(new Stage());
                    primaryStage.close();
                });

                scene.setOnKeyPressed( f -> {
                    if (f.getCode() == KeyCode.ESCAPE) {
                        GameMain gameMain = new GameMain();
                        try {
                            gameMain.start(new Stage());
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        primaryStage.close();

                    }
                });
            } else {

                move(snake, apple, gameBoard, rectangles);
            }


        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }


    public Apple randomAppleLocation(ArrayList<ArrayList<Rectangle>> rectangles) {

        Apple apple = new Apple(Color.RED);

        rectangles.get((int)apple.getLocation().getX()).get((int)apple.getLocation().getY()).setFill(apple.getAppleColor());

        return apple;
    }

    public boolean isMatching(SnakeObject snake, Apple apple, ArrayList<ArrayList<Integer>> gameBoard, ArrayList<ArrayList<Rectangle>> rectangles) {
        if ((int)snake.getLength().get(0).getLocation().getX() == (int) apple.getLocation().getX() && (int)snake.getLength().get(0).getLocation().getY() == (int) apple.getLocation().getY()) {
            randomAppleLocation(rectangles);
            return true;
        }
        return false;
    }


    public void move(SnakeObject snake, Apple apple, ArrayList<ArrayList<Integer>> gameBoard, ArrayList<ArrayList<Rectangle>> rectangles) {

        try {

            for (int i = snake.getLength().size() - 1; i > 0; i--) {
                snake.getLength().get(i).setLocation(new Point2D(snake.getLength().get(i - 1).getLocation().getX(), snake.getLength().get(i - 1).getLocation().getY()));
            }

            switch (snake.getDirection()) {
                case NORTH:
                    Point2D location = snake.getLength().get(0).getLocation();
                    snake.getLength().get(0).setLocation(new Point2D(location.getX(), location.getY() - 1));

                    if (snake.getLength().get(0).getLocation().getY() < 0) {
                        gameOver = true;
                    }
                    break;
                case EAST:
                    Point2D location1 = snake.getLength().get(0).getLocation();
                    snake.getLength().get(0).setLocation(new Point2D(location1.getX() + 1, location1.getY()));

                    if (snake.getLength().get(0).getLocation().getX() > 20) {
                        gameOver = true;
                    }
                    break;
                case SOUTH:
                    Point2D location2 = snake.getLength().get(0).getLocation();
                    snake.getLength().get(0).setLocation(new Point2D(location2.getX(), location2.getY() + 1));

                    if (snake.getLength().get(0).getLocation().getY() > 20) {
                        gameOver = true;
                    }
                    break;
                case WEST:
                    Point2D location3 = snake.getLength().get(0).getLocation();
                    snake.getLength().get(0).setLocation(new Point2D(location3.getX() - 1, location3.getY()));

                    if (snake.getLength().get(0).getLocation().getX() < 0) {
                        gameOver = true;
                    }

            }


            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 20; j++) {
                    rectangles.get(i).get(j).setFill(Color.BLACK);
                }
            }

            rectangles.get((int) apple.getLocation().getX()).get((int) apple.getLocation().getY()).setFill(Color.RED);

            for (Body b : snake.getLength()) {
                rectangles.get((int) b.getLocation().getX()).get((int) b.getLocation().getY()).setFill(Color.BLUE);
            }


            for (int i = 1; i < snake.getLength().size(); i++) {
                if (snake.getLength().get(0).getLocation().getX() == snake.getLength().get(i).getLocation().getX() && snake.getLength().get(0).getLocation().getY() == snake.getLength().get(i).getLocation().getY()) {
                    gameOver = true;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            gameOver = true;
        }




    }

    public int getSpeed(SnakeObject snake) {
        return 500 / snake.getLength().size();


    }
}
