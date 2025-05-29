package com.example.model1;

import com.example.model1.domains.HotelWithDate;
import com.example.model1.domains.Reservation;
import com.example.model1.domains.SpecialOffer;
import com.example.model1.events.HotelChangeEvent;
import com.example.model1.observer.Observer;
import com.example.model1.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ClientController implements Observer<HotelChangeEvent> {

    private final ObservableList<HotelWithDate> observableList = FXCollections.observableArrayList();
    private Service service;// = new Service(locationRepository, hotelRepository, specialOfferRepository, clientRepository);

    private List<SpecialOffer> list = new ArrayList<>();
    private Long id;

    @FXML
    Label name;

    @FXML
    TableView<HotelWithDate> tableView;

    @FXML
    TableColumn<HotelWithDate, String> hotelName;
    @FXML
    TableColumn<HotelWithDate, String> locationName;
    @FXML
    TableColumn<HotelWithDate, Date> startDate;
    @FXML
    TableColumn<HotelWithDate, Date> endDate;

    @FXML
    private TextField nNightsTF;


    public void setId(Long id) {
        System.out.println(id);
        this.id = id;
    }


    @FXML
    private void initialize() {
        hotelName.setCellValueFactory(new PropertyValueFactory<>("hotelName"));
        locationName.setCellValueFactory(new PropertyValueFactory<>("locationName"));
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        tableView.setItems(observableList);
        //System.out.println("id=" + id);
        //name.setText(service.findClient(id).getName());
    }

    public void setService(Service service) {
        this.service = service;
        service.addObserver(this);
        initModel();
    }

    private void initModel() {
        System.out.println("id=" + id);
        name.setText(service.findClient(id).getName());
        LocalDate localDate = LocalDate.now();
        list = service.offers(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()), service.findClient(id).getFidelityGrade());
        List<HotelWithDate> hotelChangeEventList = new ArrayList<>();
        for (var l : list) {
            HotelWithDate hotel = new HotelWithDate(service.findHotel(l.getHotelId()).getHotelName(), service.findLocation(service.findHotel(l.getHotelId()).getLocationId()).getLocationName(), l.getStartDate(), l.getEndDate());
            hotelChangeEventList.add(hotel);
        }
        observableList.addAll(hotelChangeEventList);
        tableView.setItems(observableList);
    }

    @Override
    public void update(HotelChangeEvent hotelChangeEvent) {
        initModel();
    }

    public void onBookPush(ActionEvent actionEvent) {
        HotelWithDate hotel = (HotelWithDate) tableView.getSelectionModel().getSelectedItem();
        Double hotelName = service.findHotelByName(hotel.getHotelName());
        Double id1 = 1.0;
        for (var l : service.getAll()) {
            if (Objects.equals(id1, l.getReservationId())) {
                id1 += 1;
            }
        }
        Reservation reservation = new Reservation(id1, Double.valueOf(id), hotelName, hotel.getStartDate(), Integer.parseInt(nNightsTF.getText()));
        service.addReservation(reservation);
    }
}
