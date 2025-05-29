module com.example.zboruri {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.zboruri to javafx.fxml;
    exports com.example.zboruri;

    opens com.example.zboruri.domain to javafx.fxml;
    exports com.example.zboruri.domain;

    opens com.example.zboruri.controller to javafx.fxml;
    exports com.example.zboruri.controller;
}