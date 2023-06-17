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

public class AddBookController {
    private Service service;
    public void setService(Service service){
        this.service = service;
    }

    @FXML
    private TextField txtTitlu;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtEditura;

    @FXML
    private TextField txtAn;

    @FXML
    void clickAddBook(MouseEvent event) throws IOException {
        if(Objects.equals(txtTitlu.getText(), "") || Objects.equals(txtAuthor.getText(), "") || Objects.equals(txtEditura.getText(), "") || Objects.equals(txtAn.getText(), "") ){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Message.fxml"));
            Parent root = fxmlLoader.load();
            MessageController ctrl = fxmlLoader.getController();
            ctrl.setMessage("Trebuie completate toate campurile corect!");
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }else{
            String titlu = txtTitlu.getText();
            String autor = txtAuthor.getText();
            String editura = txtEditura.getText();
            int an_publ = 0;
            try{
                an_publ = Integer.parseInt(txtAn.getText());
                Book book = new Book(0,titlu,autor,editura,an_publ);
                service.addBook(book);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Message.fxml"));
                Parent root = fxmlLoader.load();
                MessageController ctrl = fxmlLoader.getController();
                ctrl.setMessage("Cartea a fost adaugata cu succes!");
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                Stage st = (Stage) txtTitlu.getScene().getWindow();
                st.close();

                txtTitlu.setText("");
                txtAuthor.setText("");
                txtAn.setText("");
                txtEditura.setText("");

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
