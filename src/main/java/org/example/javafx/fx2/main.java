package org.example.javafx.fx2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class main extends Application {
    private List<Shape> shapes = new ArrayList<>();
    private Shape selectedShape;
    private double offsetX, offsetY;
    private Stage primaryStage;
    public static void fx(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        BorderPane root = new BorderPane();
        VBox buttons = new VBox();

        Button rectangleButton = new Button("Нарисовать Прямоугольник");
        Button circleButton = new Button("Нарисовать Круг");

        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        rectangleButton.setOnAction(e -> drawShape(gc, "rectangle"));
        circleButton.setOnAction(e -> drawShape(gc, "circle"));

        buttons.getChildren().addAll(rectangleButton, circleButton);
        root.setLeft(buttons);
        root.setCenter(canvas);

        canvas.setOnMousePressed(this::onMousePressed);
        canvas.setOnMouseDragged(this::onMouseDragged);
        canvas.setOnMouseReleased(this::onMouseReleased);

        primaryStage.setTitle("Shape Drawing App");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    private void drawShape(GraphicsContext gc, String shapeType) {
        double x = Math.random() * 750;
        double y = Math.random() * 550;
        Shape shape = null;

        if ("rectangle".equals(shapeType)) {
            shape = geometry2d.createRectangle(x, y);
        } else if ("circle".equals(shapeType)) {
            shape = geometry2d.createCircle(x, y);
        }

        if (shape != null) {
            shapes.add(shape);
            redrawShapes(gc);
        }
    }

    private void redrawShapes(GraphicsContext gc) {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        for (Shape shape : shapes) {
            gc.setFill((Color) shape.getFill());
            if (shape instanceof Rectangle) {
                Rectangle rectangle = (Rectangle) shape;
                gc.fillRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());

            } else if (shape instanceof Circle) {
                Circle circle = (Circle) shape;
                gc.fillOval(circle.getCenterX() - circle.getRadius(), circle.getCenterY() - circle.getRadius(), circle.getRadius() * 2, circle.getRadius() * 2);
            }
        }
    }

    private void onMousePressed(MouseEvent e) {
        for (Shape shape : shapes) {
            // Проверка попадания мыши в фигуру
            if (shape.contains(e.getX(), e.getY())) {
                selectedShape = shape;
                offsetX = e.getX() - shape.getBoundsInLocal().getMinX();
                offsetY = e.getY() - shape.getBoundsInLocal().getMinY();
                break;
            }
        }
    }

    private void onMouseDragged(MouseEvent e) {
        if (selectedShape != null) {
            double newX = e.getX() - offsetX;
            double newY = e.getY() - offsetY;

            if (selectedShape instanceof Rectangle) {
                Rectangle rectangle = (Rectangle) selectedShape;
                rectangle.setX(newX);
                rectangle.setY(newY);
            } else if (selectedShape instanceof Circle) {
                Circle circle = (Circle) selectedShape;
                circle.setCenterX(newX);
                circle.setCenterY(newY);
            }

            redrawShapes(getGraphicsContext2D());
        }
    }

    private void onMouseReleased(MouseEvent e) {
        if (selectedShape != null) {
            // Перемещение фигуры в верхний слой
            shapes.remove(selectedShape);
            shapes.add(selectedShape);
            selectedShape = null;
            redrawShapes(getGraphicsContext2D());
        }
    }

    private GraphicsContext getGraphicsContext2D() {
        // Получаем GraphicsContext из холста
        return ((Canvas) ((BorderPane) primaryStage.getScene().getRoot()).getCenter()).getGraphicsContext2D();
    }

    private void onMouseRightClicked(MouseEvent e) {
        // Изменение цвета фигуры по правому клику
        if (selectedShape != null) {
            selectedShape.setFill(geometry2d.randomColor());
            redrawShapes(getGraphicsContext2D());
        }
    }
}
