package controller;

import domain.Book;
import domain.BookRental;
import domain.Client;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.ImageCursor;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.Service;

public class ViewRentalHistoryController {
    public Service service;
    public Client client;

    public void setService(Service service) {
        this.service = service;
    }

    public void setClient(Client client) {
        this.client = client;
        getRentals();
    }

    public void getRentals(){
        idClm.setCellValueFactory(new PropertyValueFactory<BookRental,Integer>("id"));
        daysClm.setCellValueFactory(new PropertyValueFactory<BookRental, Integer>("days"));

        titleClm.setCellValueFactory(cellData -> {
            BookRental rental = cellData.getValue();
            int idBook = rental.getBook();
            Book book = service.getBook(idBook);
            return new SimpleStringProperty(book.getTitle());
        });

        authorClm.setCellValueFactory(cellData -> {
            BookRental rental = cellData.getValue();
            int idBook = rental.getBook();
            Book book = service.getBook(idBook);
            return new SimpleStringProperty(book.getAuthor());
        });

        returnedClm.setCellValueFactory(cellData -> {
            BookRental rental = cellData.getValue();
            if(rental.getReturned() == 0)
                return new SimpleStringProperty("NOT RETURNED") ;
            else
                return new SimpleStringProperty("RETURNED") ;
        });

        for(BookRental bookRental: service.getRentalHistory(client)){
            bookRentals.add(bookRental);
        }

        tabelRentalHistory.setItems(bookRentals);
    }

    @FXML
    private TableView<BookRental> tabelRentalHistory;

    @FXML
    private TableColumn<BookRental, Integer> idClm;

    @FXML
    private TableColumn<BookRental, String> titleClm;

    @FXML
    private TableColumn<BookRental, String> authorClm;

    @FXML
    private TableColumn<BookRental, Integer> daysClm;


    @FXML
    private TableColumn<BookRental, String> returnedClm;

    ObservableList<BookRental> bookRentals = FXCollections.observableArrayList();

}
