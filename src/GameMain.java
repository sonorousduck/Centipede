import Centipede.CentipedeMain;
import Snake.Snake;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Dictionary;
import java.util.Objects;

public class GameMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Settings setup = new Settings();
    private Dictionary<String, Integer> settings = setup.createDictionary();

    @Override
    public void start(Stage primaryStage) {

        Pane pane = new Pane();

        Text text = new Text("ARCADE GAMES");
        text.setFill(Color.WHITE);
        text.setFont(new Font(100));
        text.setX(settings.get("width")/ 18);
        text.setY(100);



        ImageView snakeButton = new ImageView(new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("SnakeButton.png")).toString(), true));
        snakeButton.setPreserveRatio(true);
        snakeButton.setFitHeight(200);
        snakeButton.setX((settings.get("width") + snakeButton.getFitWidth()) / 2.5);
        snakeButton.setY(settings.get("height")/ 4);

        snakeButton.setOnMouseClicked(e -> {
            Snake snake = new Snake();
            snake.start(new Stage());
            primaryStage.close();
        });


        ImageView centipedeButton = new ImageView(new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("Centipede.png")).toString(), true));
        centipedeButton.setPreserveRatio(true);
        centipedeButton.setFitHeight(200);


        centipedeButton.setX((settings.get("width") + centipedeButton.getFitWidth()) / 2.5);
        centipedeButton.setY(settings.get("height")/ 2);

        centipedeButton.setOnMouseClicked(e -> {
            CentipedeMain centipede = new CentipedeMain();
            centipede.start(new Stage());
            primaryStage.close();
        });




        pane.getChildren().addAll(text, centipedeButton, snakeButton);


        Scene scene = new Scene(pane, settings.get("width"), settings.get("height"));
        scene.setFill(Color.BLACK);

        primaryStage.setScene(scene);
        primaryStage.show();
        

    }
}
