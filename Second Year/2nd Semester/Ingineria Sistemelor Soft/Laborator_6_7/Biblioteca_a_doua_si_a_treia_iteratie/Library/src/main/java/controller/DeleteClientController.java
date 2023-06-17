package controller;

import domain.Book;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.Service;

import java.io.IOException;
import java.util.Objects;

public class DeleteClientController {
    private Service service;

    public void setService(Service service) {
        this.service = service;
    }

    @FXML
    private TextField txtId;

    @FXML
    void clickDeleteClient(MouseEvent event) throws IOException {
        if(Objects.equals(txtId.getText(), "")){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Message.fxml"));
            Parent root = fxmlLoader.load();
            MessageController ctrl = fxmlLoader.getController();
            ctrl.setMessage("Introduceti id-ul!");
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }else{
            int idDel = Integer.parseInt(txtId.getText());
            try{
                idDel = Integer.parseInt(txtId.getText());
                service.deleteClient(idDel);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Message.fxml"));
                Parent root = fxmlLoader.load();
                MessageController ctrl = fxmlLoader.getController();
                ctrl.setMessage("Clientul a fost sters!");
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                Stage st = (Stage) txtId.getScene().getWindow();
                st.close();

                txtId.setText("");
            }catch (Exception e){
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Message.fxml"));
                Parent root = fxmlLoader.load();
                MessageController ctrl = fxmlLoader.getController();
                ctrl.setMessage("Id-ul este de tip numeric!");
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            }
        }
    }

}
