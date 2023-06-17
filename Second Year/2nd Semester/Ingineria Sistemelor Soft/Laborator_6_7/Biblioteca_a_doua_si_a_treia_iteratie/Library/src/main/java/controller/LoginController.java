package controller;

import domain.Client;
import domain.Librarian;
import domain.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.Service;

import java.io.IOException;

public class LoginController {
    private Service service;

    public void setService(Service service){
        this.service = service;
    }

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnLogin;

    @FXML
    void loginClick(MouseEvent event) throws IOException {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        User user = service.findUser(username,password);
        if(user instanceof Client){
            Stage st = (Stage) btnLogin.getScene().getWindow();
            st.close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ClientView.fxml"));
            Parent root = fxmlLoader.load();
            ClientController ctrl = fxmlLoader.getController();
            ctrl.setService(this.service);
            ctrl.setClient((Client) user);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Welcome " + ((Client) user).getLast_name());
            stage.show();
        }
        else if(user instanceof Librarian){
            Stage st = (Stage) btnLogin.getScene().getWindow();
            st.close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/LibrarianView.fxml"));
            Parent root = fxmlLoader.load();
            LibrarianController ctrl = fxmlLoader.getController();
            ctrl.setService(this.service);
            ctrl.setLibrarian((Librarian) user);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Welcome " + ((Librarian) user).getLast_name());
            stage.show();

        }
        else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Message.fxml"));
            Parent root = fxmlLoader.load();
            MessageController ctrl = fxmlLoader.getController();
            ctrl.setMessage("Datele de conectare sunt gresite!");
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            txtPassword.setText("");
            txtUsername.setText("");
        }

    }

}
