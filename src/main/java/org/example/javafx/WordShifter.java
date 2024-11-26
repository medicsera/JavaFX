package org.example.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WordShifter extends Application {
    private boolean isFirstToSecond = true;

    @Override
    public void start(Stage primaryStage) {
        TextField firstField = new TextField();
        TextField secondField = new TextField();
        Button switchButton = new Button("→");

        switchButton.setOnAction(e -> {
            if (isFirstToSecond) {
                secondField.setText(firstField.getText());
                switchButton.setText("←");
            } else {
                firstField.setText(secondField.getText());
                switchButton.setText("→");
            }
            isFirstToSecond = !isFirstToSecond;
        });

        VBox layout = new VBox(10, firstField, secondField, switchButton);
        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setTitle("Перекидыватель слов");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}