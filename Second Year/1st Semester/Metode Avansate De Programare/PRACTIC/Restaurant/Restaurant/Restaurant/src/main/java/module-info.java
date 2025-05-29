module com.example.restaurant {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.restaurant to javafx.fxml;
    exports com.example.restaurant;

    opens com.example.restaurant.domain to javafx.fxml;
    exports com.example.restaurant.domain;

    opens com.example.restaurant.controller to javafx.fxml;
    exports com.example.restaurant.controller;
}