import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Dictionary;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Settings setup = new Settings();
    private Dictionary<String, Integer> settings = setup.createDictionary();

    @Override
    public void start(Stage primaryStage) {

        VBox vBox = new VBox();

        Button game1 = new Button("Game 1");
        Button game2 = new Button("Game 2");
        Button game3 = new Button("Game 3");

        vBox.getChildren().addAll(game1, game2, game3);

        Scene scene = new Scene(vBox, 800, 800);

        primaryStage.setScene(scene);
        primaryStage.show();
        

    }
}
