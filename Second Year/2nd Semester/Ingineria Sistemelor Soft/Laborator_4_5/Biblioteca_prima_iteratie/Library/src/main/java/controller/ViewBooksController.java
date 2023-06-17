package controller;

import domain.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.Service;

import java.util.Collections;
import java.util.Comparator;

public class ViewBooksController {
    private Service service;
    public void setService(Service service){
        this.service = service;

        getBooks();

    }

    public void getBooks(){
        clmId.setCellValueFactory(new PropertyValueFactory<Book,Integer>("id"));
        clmTitle.setCellValueFactory(new PropertyValueFactory<Book,String>("title"));
        clmAutor.setCellValueFactory(new PropertyValueFactory<Book,String>("author"));
        clmEditura.setCellValueFactory(new PropertyValueFactory<Book,String>("publishing_house"));
        clmAn.setCellValueFactory(new PropertyValueFactory<Book,Integer>("year_of_publication"));
        for(Book book: service.getAllBooks()){
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
}
