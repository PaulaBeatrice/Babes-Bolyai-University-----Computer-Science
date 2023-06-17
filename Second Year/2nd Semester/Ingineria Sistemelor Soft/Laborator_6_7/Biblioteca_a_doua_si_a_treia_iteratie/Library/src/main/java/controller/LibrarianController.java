package controller;

import domain.Librarian;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.Service;

import java.io.IOException;


public class LibrarianController {
    private Librarian librarian;
    public void setLibrarian(Librarian librarian){
        this.librarian = librarian;
    }

    private Service service;
    public void setService(Service service){
        this.service = service;
    }

    @FXML
    void clickAddBook(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AddBookView.fxml"));
        Parent root = fxmlLoader.load();
        AddBookController ctrl = fxmlLoader.getController();
        ctrl.setService(service);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void clickAddClient(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AddClientView.fxml"));
        Parent root = fxmlLoader.load();
        AddClientController ctrl = fxmlLoader.getController();
        ctrl.setService(service);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void clickDeleteBook(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/DeleteBookView.fxml"));
        Parent root = fxmlLoader.load();
        DeleteBookController ctrl = fxmlLoader.getController();
        ctrl.setService(service);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void clickDeleteClient(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/DeleteClientView.fxml"));
        Parent root = fxmlLoader.load();
        DeleteClientController ctrl = fxmlLoader.getController();
        ctrl.setService(service);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void clickUpdateClient(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/UpdateClientView.fxml"));
        Parent root = fxmlLoader.load();
        UpdateClientController ctrl = fxmlLoader.getController();
        ctrl.setService(service);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void clickViewBooks(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ViewBooksView.fxml"));
        Parent root = fxmlLoader.load();
        ViewBooksController ctrl = fxmlLoader.getController();
        ctrl.setService(service);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void clickViewClients(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ViewClientsView.fxml"));
        Parent root = fxmlLoader.load();
        ViewClientsController ctrl = fxmlLoader.getController();
        ctrl.setService(service);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

}

