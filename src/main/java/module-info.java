module org.example.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;

    exports org.example.javafx.fx1;
    exports org.example.javafx.fx2;
    opens org.example.javafx.fx1 to javafx.fxml;
    opens org.example.javafx.fx2 to javafx.fxml;
}