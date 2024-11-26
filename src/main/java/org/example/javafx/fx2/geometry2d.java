package org.example.javafx.fx2;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import java.util.Random;

public class geometry2d {

    private static final Random random = new Random();

    private static double randomSize() {
        return 20 + random.nextDouble() * 80; // Размер от 20 до 100
    }

    static Color randomColor() {
        return Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    public static Shape createRectangle(double x, double y) {
        Rectangle rectangle = new Rectangle(x, y, randomSize(), randomSize());
        rectangle.setFill(randomColor());
        return rectangle;
    }

    public static Shape createCircle(double x, double y) {
        Circle circle = new Circle(x, y, randomSize() / 2);
        circle.setFill(randomColor());
        return circle;
    }


}
