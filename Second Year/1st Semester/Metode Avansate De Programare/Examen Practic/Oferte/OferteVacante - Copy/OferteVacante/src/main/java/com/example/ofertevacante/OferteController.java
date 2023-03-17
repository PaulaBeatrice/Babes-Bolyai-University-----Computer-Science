package com.example.ofertevacante;

import com.example.ofertevacante.domain.*;
import com.example.ofertevacante.repository.Repository;
import com.example.ofertevacante.repository.factory.RepositoryEntity;
import com.example.ofertevacante.repository.factory.RepositoryFactory;
import com.example.ofertevacante.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;

public class OferteController implements Initializable {
    Hotel hotel;

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    Repository<Double, Location> locatiiRepository = RepositoryFactory.getInstance().createRepository(RepositoryEntity.LOCATII);
    Repository<Double, Hotel> hotelRepository = RepositoryFactory.getInstance().createRepository(RepositoryEntity.HOTELURI);
    Repository<Double, SpecialOffer> oferteRepository = RepositoryFactory.getInstance().createRepository(RepositoryEntity.OFERTE);

    Repository<Long, Client> clientRepository = RepositoryFactory.getInstance().createRepository(RepositoryEntity.CLIENTI);
    Repository<Double, Reservation> reservationRepository = RepositoryFactory.getInstance().createRepository(RepositoryEntity.RESERVATIONS);
    Service service = new Service(locatiiRepository,hotelRepository,oferteRepository,clientRepository,reservationRepository);
    @FXML
    private Button buton_oferte;

    @FXML
    private TableColumn<SpecialOffer, LocalDateTime> end_date;

    @FXML
    private TableColumn<SpecialOffer, Double> id;

    @FXML
    private TableColumn<SpecialOffer, Double> id_hotel;

    @FXML
    private TableColumn<SpecialOffer, Integer> percent;

    @FXML
    private DatePicker picker_end;

    @FXML
    private DatePicker picker_start;

    @FXML
    private TableColumn<SpecialOffer, LocalDateTime> start_date;

    @FXML
    private TableView<SpecialOffer> table_oferte;
    ObservableList<SpecialOffer> lista = FXCollections.observableArrayList();

    @FXML
    void afisare_oferte(MouseEvent event) {
        LocalDateTime s = picker_start.getValue().atStartOfDay();
        LocalDateTime e = picker_end.getValue().atStartOfDay();
        for(SpecialOffer o:service.getAllOferte())
        {
            if(o.getStart_date().isAfter(s) && o.getEnd_date().isBefore(e) && Objects.equals(o.getHotel_id(), hotel.getId()))
                lista.add(o);
        }
        table_oferte.setItems(lista);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id.setCellValueFactory(new PropertyValueFactory<SpecialOffer,Double>("id"));
        id_hotel.setCellValueFactory(new PropertyValueFactory<SpecialOffer,Double>("hotel_id"));
        start_date.setCellValueFactory(new PropertyValueFactory<SpecialOffer,LocalDateTime>("start_date"));
        end_date.setCellValueFactory(new PropertyValueFactory<SpecialOffer,LocalDateTime>("end_date"));
        percent.setCellValueFactory(new PropertyValueFactory<SpecialOffer, Integer>("percent"));
    }


}
