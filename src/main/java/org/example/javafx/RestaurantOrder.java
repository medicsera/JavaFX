package org.example.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RestaurantOrder extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label menuLabel = new Label("Выберите блюда:");
        CheckBox dish1 = new CheckBox("Паста - 10.00");
        CheckBox dish2 = new CheckBox("Пицца - 15.00");
        CheckBox dish3 = new CheckBox("Салат - 5.00");

        TextField quantity1 = new TextField("0");
        TextField quantity2 = new TextField("0");
        TextField quantity3 = new TextField("0");

        Button orderButton = new Button("Заказать");
        TextArea receipt = new TextArea();

        orderButton.setOnAction(e -> {
            StringBuilder orderDetails = new StringBuilder();
            double total = 0;

            if (dish1.isSelected()) {
                int qty = Integer.parseInt(quantity1.getText());
                double cost = qty * 10.00;
                total += cost;
                orderDetails.append("Паста: ").append(qty).append(" шт. - ").append(cost).append("\n");
            }

            if (dish2.isSelected()) {
                int qty = Integer.parseInt(quantity2.getText());
                double cost = qty * 15.00;
                total += cost;                orderDetails.append("Пицца: ").append(qty).append(" шт. - ").append(cost).append("\n");
            }

            if (dish3.isSelected()) {
                int qty = Integer.parseInt(quantity3.getText());
                double cost = qty * 5.00;
                total += cost;
                orderDetails.append("Салат: ").append(qty).append(" шт. - ").append(cost).append("\n");
            }

            orderDetails.append("Итого: ").append(total);
            receipt.setText(orderDetails.toString());
        });

        VBox layout = new VBox(10, menuLabel, dish1, quantity1, dish2, quantity2, dish3, quantity3, orderButton, receipt);
        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setTitle("Заказ в ресторане");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}