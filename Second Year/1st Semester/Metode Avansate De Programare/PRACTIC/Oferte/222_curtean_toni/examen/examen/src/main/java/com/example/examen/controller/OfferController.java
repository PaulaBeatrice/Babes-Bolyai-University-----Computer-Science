package com.example.examen.controller;

import com.example.examen.domain.Hotel;
import com.example.examen.domain.SpecialOffer;
import com.example.examen.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;

import java.text.DateFormatSymbols;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class OfferController {

    Service service;

    @FXML
    DatePicker datePickerStart;

    @FXML
    DatePicker datePickerEnd;
    Hotel hotel;

    @FXML
    ListView<String> offersView;

    ObservableList<String> offers= FXCollections.observableArrayList();
    public void setService(Service service){
        this.service=service;
    }

    public void setHotel(Hotel hotel){
        this.hotel=hotel;
    }

    public void init() {
        offersView.setItems(offers);

    }

    public void onSelectedDate(KeyEvent keyEvent){
        offers.clear();
        if(datePickerStart.getValue()==null || datePickerEnd.getValue()==null)
                return;
        LocalDate startLocalDate=datePickerStart.getValue();
        LocalDate endLocalDate=datePickerEnd.getValue();
        for(SpecialOffer specialOffer:service.getOffers(hotel,startLocalDate,endLocalDate))
           offers.add("Offer id: "+specialOffer.getSpecialOfferId().toString()+" Hotel Id:"+specialOffer.getHotelId()+" Percents:"+specialOffer.getHotelId().toString());
    }
}
