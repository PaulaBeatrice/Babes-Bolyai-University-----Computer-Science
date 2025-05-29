package com.example.model1;

import com.example.model1.domains.*;
import com.example.model1.events.HotelChangeEvent;
import com.example.model1.observer.Observer;
import com.example.model1.repos.*;
import com.example.model1.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;

public class MainPage implements Observer<HotelChangeEvent> {

    Repository<Double, Location> locationRepository = new locationRepo<>();
    Repository<Double, Hotel> hotelRepository = new HotelRepo<>();
    Repository<Double, SpecialOffer> specialOfferRepository = new SpecialOfferRepo<>();
    Repository<Double, Reservation> repositoryRepository = new ReservationRepo<>();
    Repository<Long, Client> clientRepository = new ClientRepo<>();

    private final ObservableList<Hotel> hotelObservableList = FXCollections.observableArrayList();
    private Service service = new Service(locationRepository, hotelRepository, specialOfferRepository, clientRepository, repositoryRepository);

    @FXML
    private TableView<Hotel> tableView;
    @FXML
    private TableColumn<Hotel, Double> hId;
    @FXML
    private TableColumn<Hotel, Double> lId;
    @FXML
    private TableColumn<Hotel, String> hName;
    @FXML
    private TableColumn<Hotel, Integer> nOfRooms;
    @FXML
    private TableColumn<Hotel, Double> pPerNight;
    @FXML
    private TableColumn<Hotel, String> hType;

    @FXML
    private ComboBox<String> comboBox;// = new ComboBox<>(FXCollections.observableArrayList(service.locationNames()));

    @Override
    public void update(HotelChangeEvent hotelChangeEvent) {
        initialize();
    }

    @FXML
    public void initialize() {
        hId.setCellValueFactory(new PropertyValueFactory<>("hotelId"));
        lId.setCellValueFactory(new PropertyValueFactory<>("locationId"));
        hName.setCellValueFactory(new PropertyValueFactory<>("hotelName"));
        nOfRooms.setCellValueFactory(new PropertyValueFactory<>("noRooms"));
        pPerNight.setCellValueFactory(new PropertyValueFactory<>("pricePerNight"));
        hType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tableView.setItems(hotelObservableList);
        comboBox.getItems().addAll(service.locationNames());
    }

    public void setService(Service studentService) {
        this.service = studentService;
        studentService.addObserver(this);
        // initModel();
    }

    private void initModel() {

    }

    public void onSubmitPush(ActionEvent actionEvent) {
        String locationName = comboBox.getValue();
        hotelObservableList.setAll(service.findAllByLocation(locationName));
    }

    private void onSelectedHotel(MouseEvent mouseEvent) throws IOException {

    }

    public void onSpecialOffersPush(ActionEvent actionEvent) throws IOException {
        Hotel hotel = (Hotel) tableView.getSelectionModel().getSelectedItem();
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("specialOffer.fxml"));
        Parent fxmlLoader = loader.load();
        SpecialOfferController specialOffer = loader.getController();
        specialOffer.setId(hotel.getHotelId());
        Scene scene = new Scene(fxmlLoader, 600, 400);
        stage.setScene(scene);
        stage.show();
    }
}