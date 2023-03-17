module com.example.clinica {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.clinica to javafx.fxml;
    exports com.example.clinica;

    opens com.example.clinica.domain to javafx.fxml;
    exports com.example.clinica.domain;

    opens com.example.clinica.controller to javafx.fxml;
    exports com.example.clinica.controller;
}