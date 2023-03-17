package com.example.ofertevacante;

import com.example.ofertevacante.domain.*;
import com.example.ofertevacante.repository.Repository;
import com.example.ofertevacante.repository.factory.RepositoryEntity;
import com.example.ofertevacante.repository.factory.RepositoryFactory;
import com.example.ofertevacante.service.Service;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    Long client;

    public void setClient(Long cl) {
        this.client = cl;
        Repository<Double, Location> locatiiRepository = RepositoryFactory.getInstance().createRepository(RepositoryEntity.LOCATII);
        Repository<Double, Hotel> hotelRepository = RepositoryFactory.getInstance().createRepository(RepositoryEntity.HOTELURI);
        Repository<Double, SpecialOffer> oferteRepository = RepositoryFactory.getInstance().createRepository(RepositoryEntity.OFERTE);

        Repository<Long, Client> clientRepository = RepositoryFactory.getInstance().createRepository(RepositoryEntity.CLIENTI);
        Repository<Double, Reservation> reservationRepository = RepositoryFactory.getInstance().createRepository(RepositoryEntity.RESERVATIONS);
        Service service = new Service(locatiiRepository,hotelRepository,oferteRepository,clientRepository,reservationRepository);

        locatie.setCellValueFactory(p->{
            String numeLocatie = null;
            SpecialOffer of = p.getValue();
            // extrag locatia pe baza id-ului hotelului din oferta
            double locatie = 0;
            for(Hotel h:service.getAllHoteluri()){
                    if(h.getId().equals(of.getHotel_id())){
                        locatie = h.getLocationId();
                    }
                }
            for(Location l:service.getAllLocatii())
                if(l.getId().equals(locatie))
                    numeLocatie = l.getLocationName();
            return new SimpleStringProperty(numeLocatie);
        });

        nume_hotel.setCellValueFactory(p->{
            String numeHotel = null;
            SpecialOffer of = p.getValue();
            // extrag locatia pe baza id-ului hotelului din oferta
            double locatie = 0;
            for(Hotel h: service.getAllHoteluri()){
                if(Objects.equals(h.getId(), of.getHotel_id())){
                    numeHotel = h.getName();
                }
            }
            return new SimpleStringProperty(numeHotel);
        });
        start_date.setCellValueFactory(new PropertyValueFactory<SpecialOffer,LocalDateTime>("start_date"));
        start_date.setCellValueFactory(new PropertyValueFactory<SpecialOffer,LocalDateTime>("end_date"));



        Client client_actual = null;
        for(Client c:service.getAllClienti()){
            if(Objects.equals(c.getId(), client)){
                System.out.println(c.getId());
                client_actual = c;
            }
        }

        for(SpecialOffer specialOffer:service.getAllOferte()){
            assert client_actual != null;
            if(client_actual.getFidelity_grade() > specialOffer.getPercent()
                    && specialOffer.getStart_date().isAfter(LocalDateTime.now()))
                lista.add(specialOffer);
        }

        oferteClient.setItems(lista);
    }

    @FXML
    private TableColumn<SpecialOffer, LocalDateTime> end_date;

    @FXML
    private TableColumn<SpecialOffer, String> locatie;

    @FXML
    private TableColumn<SpecialOffer,String> nume_hotel;

    @FXML
    private TableView<SpecialOffer> oferteClient;

    @FXML
    private TableColumn<SpecialOffer, LocalDateTime> start_date;

    ObservableList<SpecialOffer> lista = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    @FXML
    private TextField nrNopti;

    @FXML
    void adaugaRezervare(MouseEvent event) {
        Repository<Double, Location> locatiiRepository = RepositoryFactory.getInstance().createRepository(RepositoryEntity.LOCATII);
        Repository<Double, Hotel> hotelRepository = RepositoryFactory.getInstance().createRepository(RepositoryEntity.HOTELURI);
        Repository<Double, SpecialOffer> oferteRepository = RepositoryFactory.getInstance().createRepository(RepositoryEntity.OFERTE);

        Repository<Long, Client> clientRepository = RepositoryFactory.getInstance().createRepository(RepositoryEntity.CLIENTI);
        Repository<Double, Reservation> reservationRepository = RepositoryFactory.getInstance().createRepository(RepositoryEntity.RESERVATIONS);
        Service service = new Service(locatiiRepository,hotelRepository,oferteRepository,clientRepository,reservationRepository);

        double id_r = 0;
        for(Reservation r:service.getAllReservations())
            if(r.getId() > id_r)
                id_r = r.getId();




        Integer nr_nopti = Integer.valueOf(nrNopti.getText());
        LocalDateTime data = oferteClient.getSelectionModel().getSelectedItem().getStart_date();
        // client - id client
        Double hotel = oferteClient.getSelectionModel().getSelectedItem().getHotel_id();
        Reservation r = new Reservation(id_r+1, client,hotel,data,nr_nopti);
        service.add(r);

    }
}
