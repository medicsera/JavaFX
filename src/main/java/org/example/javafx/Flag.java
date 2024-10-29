package org.example.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Flag extends Application {
    private ToggleGroup group1, group2, group3;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Текстовый флаг");

        // Создание панелей для каждой полосы флага
        group1 = new ToggleGroup();
        VBox panel1 = createColorSelectionPanel("Полоса 1", group1);

        group2 = new ToggleGroup();
        VBox panel2 = createColorSelectionPanel("Полоса 2", group2);

        group3 = new ToggleGroup();
        VBox panel3 = createColorSelectionPanel("Полоса 3", group3);

        Button drawButton = new Button("Нарисовать");
        drawButton.setOnAction(e -> openFlagWindow());

        VBox mainPanel = new VBox(10, panel1, panel2, panel3, drawButton);
        Scene scene = new Scene(mainPanel, 300, 450);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createColorSelectionPanel(String title, ToggleGroup group) {
        VBox panel = new VBox();
        panel.setSpacing(5);
        panel.setBorder(new Border(new BorderStroke(null, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));

        Label label = new Label(title);
        panel.getChildren().add(label);

        String[] colors = {"Красный", "Зеленый", "Синий", "Желтый", "Белый"};

        for (String color : colors) {
            RadioButton radioButton = new RadioButton(color);
            radioButton.setToggleGroup(group);
            panel.getChildren().add(radioButton);
        }

        return panel;
    }

    private void openFlagWindow() {
        Stage flagStage = new Stage();
        flagStage.setTitle("Флаг");

        Canvas canvas = new Canvas(500, 300); // Canvas для рисования флага
        drawFlag(canvas.getGraphicsContext2D());

        VBox vbox = new VBox(canvas);
        Scene scene = new Scene(vbox);

        flagStage.setScene(scene);
        flagStage.setResizable(false);
        flagStage.show();
    }

    private void drawFlag(GraphicsContext gc) {
        gc.clearRect(0, 0, 500, 300); // Очистить холст

        String color1 = getSelectedColor(group1);
        String color2 = getSelectedColor(group2);
        String color3 = getSelectedColor(group3);

        // Рисуем флаг в три полосы
        gc.setFill(getColor(color1));
        gc.fillRect(0, 0, 500, 100);
        gc.setFill(getColor(color2));
        gc.fillRect(0, 100, 500, 100);
        gc.setFill(getColor(color3));
        gc.fillRect(0, 200, 500, 100);
    }

    private String getSelectedColor(ToggleGroup group) {
        RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
        return (selectedRadioButton != null) ? selectedRadioButton.getText() : "Не выбрано";
    }

    private javafx.scene.paint.Paint getColor(String color) {
        switch (color) {
            case "Красный": return javafx.scene.paint.Color.RED;
            case "Зеленый": return javafx.scene.paint.Color.GREEN;
            case "Синий": return javafx.scene.paint.Color.BLUE;
            case "Желтый": return javafx.scene.paint.Color.YELLOW;
            case "Белый": return javafx.scene.paint.Color.WHITE;
            default: return javafx.scene.paint.Color.GRAY; // Цвет по умолчанию
        }
    }
}
