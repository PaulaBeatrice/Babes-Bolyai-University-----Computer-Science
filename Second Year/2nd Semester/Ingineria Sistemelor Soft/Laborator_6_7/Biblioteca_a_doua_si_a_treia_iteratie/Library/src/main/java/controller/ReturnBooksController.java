package controller;

import domain.Book;
import domain.BookRental;
import domain.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.Service;

import java.io.IOException;

public class ReturnBooksController {
    public Service service;
    public Client client;

    public void setService(Service service) {
        this.service = service;
    }

    public void setClient(Client client) {
        this.client = client;

        clmId.setCellValueFactory(new PropertyValueFactory<Book,Integer>("id"));
        clmTitle.setCellValueFactory(new PropertyValueFactory<Book,String>("title"));
        clmAutor.setCellValueFactory(new PropertyValueFactory<Book,String>("author"));
        clmEditura.setCellValueFactory(new PropertyValueFactory<Book,String>("publishing_house"));
        clmAn.setCellValueFactory(new PropertyValueFactory<Book,Integer>("year_of_publication"));
        for(Book book: service.getRentedBooks(client)){
            books.add(book);
        }
        tabelBooks.setItems(books);
    }

    @FXML
    private TableView<Book> tabelBooks;

    @FXML
    private TableColumn<Book, Integer> clmId;

    @FXML
    private TableColumn<Book, String> clmTitle;

    @FXML
    private TableColumn<Book, String> clmAutor;

    @FXML
    private TableColumn<Book, String> clmEditura;

    @FXML
    private TableColumn<Book, Integer> clmAn;

    ObservableList<Book> books = FXCollections.observableArrayList();
    ObservableList<Book> selectedBooks = FXCollections.observableArrayList();

    @FXML
    void pickBook(MouseEvent event) {
        Book book = tabelBooks.getSelectionModel().getSelectedItem();
        selectedBooks.add(book);
    }

    @FXML
    void returnBooksClick(MouseEvent event) throws IOException {
        for(Book book: selectedBooks){
            service.returnBook(book,client);
//            System.err.println(book + " " + client);
        }
        Stage st = (Stage) tabelBooks.getScene().getWindow();
        st.close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Message.fxml"));
        Parent root = fxmlLoader.load();
        MessageController ctrl = fxmlLoader.getController();
        ctrl.setMessage("Returnarea cartilor s-a efectuat cu succes!");
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

}
