package Main;

import Centipede.CentipedeMain;
import Centipede.Settings;
import Snake.Snake;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
    public void start(Stage primaryStage) throws Exception {

        Pane pane = new Pane();

        Text text = new Text("ARCADE GAMES");
        text.setFill(Color.WHITE);
        text.setFont(new Font(100));
        text.setX(settings.get("width") / 18);
        text.setY(100);



        ImageView snakeButton = new ImageView(new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("SnakeButton.png")).toString(), true));
        snakeButton.setPreserveRatio(true);
        snakeButton.setFitHeight(200);



        snakeButton.setOnMouseClicked(e -> {
            Snake snake = new Snake();
            snake.start(new Stage());
            primaryStage.close();
        });


        ImageView centipedeButton = new ImageView(new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("Centipede.png")).toString(), true));
        centipedeButton.setPreserveRatio(true);
        centipedeButton.setFitHeight(200);



        centipedeButton.setOnMouseClicked(e -> {
            CentipedeMain centipede = new CentipedeMain();
            centipede.start(new Stage());
            primaryStage.close();
        });




        pane.getChildren().addAll(text, centipedeButton, snakeButton);


        Scene scene = new Scene(pane, settings.get("width"), settings.get("height"));
        scene.setFill(Color.BLACK);

        /**
         * Hey Look! A Binding!
         */

        text.xProperty().bind(scene.widthProperty().add(text.wrappingWidthProperty()).divide(2.5).subtract(275));


        snakeButton.yProperty().bind(scene.heightProperty().divide(4));
        snakeButton.xProperty().bind(scene.widthProperty().add(centipedeButton.getFitWidth()).divide(2.5));
        centipedeButton.yProperty().bind(scene.heightProperty().divide(2));
        centipedeButton.xProperty().bind(scene.widthProperty().add(centipedeButton.getFitWidth()).divide(2.5));


        /**
         * Doesn't look the best, but it works for a listener. :)
         */

        text.xProperty().addListener(e-> {
            text.scaleXProperty().set(scene.getWidth() / 800);
        });

        scene.widthProperty().addListener(e -> {
            centipedeButton.setFitHeight((scene.getWidth() - 600) / 1.5);
            snakeButton.setFitHeight((scene.getWidth() - 600) / 1.5);

        });




        String data = RSSReader.readRSS("https://w1.weather.gov/xml/current_obs/KLGU.rss");


        String[] lookingForTemp = data.split(" ");
        int temp = -1;


        for (String element : lookingForTemp) {
            try {
                temp = Integer.parseInt(element);
            } catch (Exception e) {

            }
        }

        Text temperature = new Text();
        if (temp == -1) {
            temperature.setText("ERROR IN UPDATING TEMPERATURE");
        } else {
            temperature.setText("Local weather in Logan: " + temp + "\u00B0 F ");
        }

        temperature.setFont(Font.font(("Arial"), FontWeight.BOLD, 20));
        temperature.setFill(Color.WHITE);
        temperature.setX(25);
        temperature.setY(750);

        pane.getChildren().addAll(temperature);








        primaryStage.setScene(scene);
        primaryStage.show();
        

    }
}
