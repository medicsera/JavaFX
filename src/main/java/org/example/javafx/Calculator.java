package org.example.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Calculator extends Application {
    private TextField display = new TextField();

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();

        display.setEditable(false);
        grid.add(display, 0, 0, 4, 1);

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        int row = 1;
        int col = 0;
        for (String label : buttons) {
            Button button = new Button(label);
            button.setPrefHeight(75);
            button.setPrefWidth(75);
            grid.add(button, col, row);
            button.setOnAction(e -> handleButtonPress(label));
            col++;
            if (col > 3) {
                col = 0;
                row++;
            }
        }

        Scene scene = new Scene(grid, 300, 300);
        primaryStage.setTitle("Калькулятор");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private double temp = 0;
    private String lastOperator = "";

    private void handleButtonPress(String label) {
        if ("0123456789".contains(label)) {
            display.setText(display.getText() + label);
        } else if ("C".equals(label)) {
            display.clear();
            temp = 0;
            lastOperator = "";
        } else if ("=".equals(label)) {
            compute(lastOperator);
            lastOperator = "";
        } else {
            if (!lastOperator.isEmpty()) {
                compute(lastOperator);
            }
            lastOperator = label;
            temp = Double.parseDouble(display.getText());
            display.clear();
        }
    }

    private void compute(String operator) {
        double currentValue = Double.parseDouble(display.getText());
        switch (operator) {
            case "+":
                display.setText(String.valueOf(temp + currentValue));
                break;
            case "-":
                display.setText(String.valueOf(temp - currentValue));
                break;
            case "*":
                display.setText(String.valueOf(temp * currentValue));
                break;
            case "/":
                if (currentValue != 0) {
                    display.setText(String.valueOf(temp / currentValue));
                } else {
                    display.setText("Ошибка: деление на 0");
                }
                break;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}