module com.example.anar {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.anar to javafx.fxml;
    exports com.example.anar;

    opens com.example.anar.domain to javafx.fxml;
    exports com.example.anar.domain;

    opens com.example.anar.controller to javafx.fxml;
    exports com.example.anar.controller;
}