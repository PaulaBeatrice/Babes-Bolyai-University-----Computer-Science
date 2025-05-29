package com.example.model1;

import com.example.model1.domains.*;
import com.example.model1.repos.*;
import com.example.model1.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class SpecialOfferController {

    Repository<Double, Location> locationRepository = new locationRepo<>();
    Repository<Double, Hotel> hotelRepository = new HotelRepo<>();
    Repository<Double, SpecialOffer> specialOfferRepository = new SpecialOfferRepo<>();
    Repository<Long, Client> clientRepository = new ClientRepo<>();
    Repository<Double, Reservation> repositoryRepository = new ReservationRepo<>();
    private final ObservableList<SpecialOffer> specialOfferObservableList = FXCollections.observableArrayList();
    private Service service = new Service(locationRepository, hotelRepository, specialOfferRepository, clientRepository, repositoryRepository);

    private Double id;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;

    @FXML
    private TableView<SpecialOffer> tableView;

    @FXML
    private TableColumn<SpecialOffer, Double> specialOfferId;
    @FXML
    private TableColumn<SpecialOffer, Double> hotelId;
    @FXML
    private TableColumn<SpecialOffer, LocalDate> startDate;
    @FXML
    private TableColumn<SpecialOffer, LocalDate> endDate;
    @FXML
    private TableColumn<SpecialOffer, Integer> percents;


    @FXML
    private void initialize() {
        specialOfferId.setCellValueFactory(new PropertyValueFactory<>("specialOfferId"));
        hotelId.setCellValueFactory(new PropertyValueFactory<>("hotelId"));
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        percents.setCellValueFactory(new PropertyValueFactory<>("percents"));
        tableView.setItems(specialOfferObservableList);
    }

    public void setId(Double id) {
        this.id = id;
    }

    public void onShowPush(ActionEvent actionEvent) {
        System.out.println(startDatePicker.getValue());
        System.out.println(endDatePicker.getValue());
        specialOfferObservableList.setAll(service.specialOffers(id, Date.from(startDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(endDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())));
    }
}
