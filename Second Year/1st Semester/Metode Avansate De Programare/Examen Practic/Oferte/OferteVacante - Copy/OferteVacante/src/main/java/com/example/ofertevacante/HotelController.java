package com.example.ofertevacante;
import com.example.ofertevacante.OferteController;
import com.example.ofertevacante.domain.*;
import com.example.ofertevacante.repository.HotelRepository;
import com.example.ofertevacante.repository.OferteRepository;
import com.example.ofertevacante.repository.Repository;
import com.example.ofertevacante.repository.factory.RepositoryEntity;
import com.example.ofertevacante.repository.factory.RepositoryFactory;
import com.example.ofertevacante.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HotelController implements Initializable {
    Repository<Double, Location> locatiiRepository = RepositoryFactory.getInstance().createRepository(RepositoryEntity.LOCATII);
    Repository<Double, Hotel> hotelRepository = RepositoryFactory.getInstance().createRepository(RepositoryEntity.HOTELURI);
    Repository<Double, SpecialOffer> oferteRepository = RepositoryFactory.getInstance().createRepository(RepositoryEntity.OFERTE);
    Repository<Long, Client> clientRepository = RepositoryFactory.getInstance().createRepository(RepositoryEntity.CLIENTI);
    Repository<Double, Reservation> reservationRepository = RepositoryFactory.getInstance().createRepository(RepositoryEntity.RESERVATIONS);
    Service service = new Service(locatiiRepository,hotelRepository,oferteRepository,clientRepository,reservationRepository);
    @FXML
    private Button buton;

    @FXML
    private ComboBox<String> combobox;

    @FXML
    private TableColumn<Hotel, String> hotel_name;

    @FXML
    private TableColumn<Hotel, Double> id;

    @FXML
    private TableColumn<Hotel, Integer> nr_camere;

    @FXML
    private TableColumn<Hotel, Double> pret;

    @FXML
    private TableView<Hotel> table;

    @FXML
    private TableColumn<Hotel, typeHotel> tip;
    ObservableList<Hotel> lista = FXCollections.observableArrayList();
    ObservableList<String> locatii = FXCollections.observableArrayList();
    @FXML
    void afisareHoteluri(MouseEvent event) {
        String locatie = combobox.getSelectionModel().getSelectedItem();
        Double id = null;
        for(Location l: service.getAllLocatii())
        {
            if(l.getLocationName().equals(locatie))
                id = l.getId();

        }
        for(Hotel hotel: service.getAllHoteluri())
        {
            if(hotel.getLocationId().equals(id))
                lista.add(hotel);
        }
        table.setItems(lista);
    }
    @FXML
    void afisareOferte(MouseEvent event) throws IOException {

//        Stage stage = new Stage();
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("oferte-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(),400,400);
//        Double id_h = table.getSelectionModel().getSelectedItem().getId();
//        stage.setTitle(String.valueOf(id_h));
//        stage.setScene(scene);
//        stage.show();

        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("oferte-view.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load(), 550, 400);
        Stage stage2 = new Stage();
        OferteController oferteController = fxmlLoader2.getController();
        oferteController.setHotel(table.getSelectionModel().getSelectedItem());
        stage2.setTitle("Oferte Hotel");
        stage2.setScene(scene2);
        stage2.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id.setCellValueFactory(new PropertyValueFactory<Hotel,Double>("id"));
        hotel_name.setCellValueFactory(new PropertyValueFactory<Hotel,String>("name"));
        nr_camere.setCellValueFactory(new PropertyValueFactory<Hotel,Integer>("nrCamere"));
        pret.setCellValueFactory(new PropertyValueFactory<Hotel,Double>("pret"));
        tip.setCellValueFactory(new PropertyValueFactory<Hotel,typeHotel>("tipHotel"));
        for(Location l:service.getAllLocatii())
        {
            locatii.add(l.getLocationName());
        }
        combobox.setItems(locatii);
    }
}
