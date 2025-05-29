module com.example.spital {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.spital to javafx.fxml;
    exports com.example.spital;

    opens com.example.spital.domain to javafx.fxml;
    exports com.example.spital.domain;

    opens com.example.spital.controller to javafx.fxml;
    exports com.example.spital.controller;
}