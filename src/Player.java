import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player {
    private int x;
    private int y;
    private int size;
    private Rectangle rectangle;



    Player(int width, int size, int height) {
        this.x = width / 2;
        this.size = size;
        this.rectangle = new Rectangle();

        rectangle.setHeight(this.size);
        rectangle.setWidth(this.size);

        rectangle.setX(width / 2);
        rectangle.setY(height - 20);

        rectangle.setFill(Color.RED);

    }


    public int getX() {
        return this.x;
    }

    public int getSize() {
        return this.size;
    }

    public Rectangle getRectangle() {
        return this.rectangle;
    }


}
