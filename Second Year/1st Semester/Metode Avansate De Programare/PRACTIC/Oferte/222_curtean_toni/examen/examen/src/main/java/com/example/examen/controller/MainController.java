package com.example.examen.controller;

import com.example.examen.Main;
import com.example.examen.domain.Hotel;
import com.example.examen.domain.Location;
import com.example.examen.service.Service;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    Service service;

    @FXML
    ComboBox<String> locationsBox;

    @FXML
    private TableView<Hotel> hotelView;

    @FXML
    public TableColumn<Hotel,String> idHotel;

    @FXML
    public TableColumn<Hotel,String> idLocatie;

    @FXML
    public TableColumn<Hotel,String> hotelName;

    @FXML
    public TableColumn<Hotel,String> noRooms;

    @FXML
    public TableColumn<Hotel,String> pricePerNight;

    @FXML
    public TableColumn<Hotel,String> type;

    ObservableList<Hotel> hotels=FXCollections.observableArrayList();

    ObservableList<String> locations=FXCollections.observableArrayList();


    public void setService(Service service){
        this.service=service;
    }

    public void init(){
        idHotel.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getHotelId().toString()));
        idLocatie.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getLocationId().toString()));
        hotelName.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getHotelName()));
        noRooms.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getNoRooms().toString()));
        pricePerNight.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getPricePerNight().toString()));
        type.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getType().toString()));

        locations.clear();
        for(Location location: service.getLocations())
            locations.add(location.getLocationName());
        locationsBox.setItems(locations);
        locationsBox.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER)
                onSelectedLocation(new ActionEvent());
        });
    }

    public void onSelectedLocation(ActionEvent actionEvent){
        hotels.clear();
        if(locationsBox.getSelectionModel().getSelectedItem()==null)
            return;
        String selected=locationsBox.getSelectionModel().getSelectedItem();
        Location location=service.getLocationByName(selected);
        if(location!=null){
            for(Hotel hotel:service.getHotelsForLocation(location.getLocationId()))
                hotels.add(hotel);
            hotelView.setItems(hotels);
        }
    }

    public void onSelectedHotel(MouseEvent mouseEvent) throws IOException {
        if(hotelView.getSelectionModel().getSelectedItem()==null)
            return;
        Hotel selectedHotel=hotelView.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader=new FXMLLoader(Main.class.getResource("offer-view.fxml"));
        Scene scene=new Scene(fxmlLoader.load(),600,360);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.setTitle("Oferte");
        stage.show();
        OfferController offerController=fxmlLoader.getController();
        offerController.setService(service);
        offerController.setHotel(selectedHotel);
        offerController.init();
    }


}
