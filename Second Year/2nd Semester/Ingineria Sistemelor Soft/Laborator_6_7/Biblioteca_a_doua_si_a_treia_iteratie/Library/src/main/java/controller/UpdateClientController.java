package controller;

import domain.Book;
import domain.Client;
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

public class UpdateClientController {
    public Service service;

    public void setService(Service service) {
        this.service = service;
    }

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtFName;

    @FXML
    private TextField txtLName;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPhNr;

    @FXML
    private TextField txtId;

    @FXML
    void clickUpdateClient(MouseEvent event) throws IOException {
        if(Objects.equals(txtId.getText(), "") || Objects.equals(txtUsername.getText(), "") || Objects.equals(txtPassword.getText(), "") || Objects.equals(txtFName.getText(), "") || Objects.equals(txtLName.getText(), "") || Objects.equals(txtEmail.getText(), "") || Objects.equals(txtPhNr.getText(), "")){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Message.fxml"));
            Parent root = fxmlLoader.load();
            MessageController ctrl = fxmlLoader.getController();
            ctrl.setMessage("Trebuie completate toate campurile corect!");
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }else{
            String us = txtUsername.getText();
            String ps = txtPassword.getText();
            String fn = txtFName.getText();
            String ln = txtLName.getText();
            String em = txtEmail.getText();
            String pn = txtPhNr.getText();
            int id = 0;
            try{
                id = Integer.parseInt(txtId.getText());
                Client client = new Client(id,us,ps,fn,ln,em,pn);
                System.out.println("------ID CONTROLLER UPDATE ------------" + id);
                service.updateClient(client);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Message.fxml"));
                Parent root = fxmlLoader.load();
                MessageController ctrl = fxmlLoader.getController();
                ctrl.setMessage("Informatiile despre client au fost actualizate cu succes!");
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                Stage st = (Stage) txtPhNr.getScene().getWindow();
                st.close();

                txtId.setText("");
                txtUsername.setText("");
                txtPassword.setText("");
                txtFName.setText("");
                txtLName.setText("");
                txtEmail.setText("");
                txtPhNr.setText("");

            }catch (Exception e){
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Message.fxml"));
                Parent root = fxmlLoader.load();
                MessageController ctrl = fxmlLoader.getController();
                ctrl.setMessage("Trebuie completate toate campurile corect!");
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            }
        }
    }

}
