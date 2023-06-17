package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MessageController {
    public void setMessage(String txt){
        txtError.setText(txt);
    }

    @FXML
    private Label txtError;

    @FXML
    private Button btnOK;

    @FXML
    void clickOK(MouseEvent event) {
        Stage stage = (Stage) btnOK.getScene().getWindow();
        stage.close();
    }
}
