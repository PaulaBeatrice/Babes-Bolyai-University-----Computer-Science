package com.example.examen;

import com.example.examen.domain.City;
import com.example.examen.domain.TrainStation;
import com.example.examen.repository.Repository;
import com.example.examen.repository.factory.RepositoryEntity;
import com.example.examen.repository.factory.RepositoryFactory;
import com.example.examen.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.StandardSocketOptions;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class PageController implements Initializable {

    @FXML
    private ComboBox<String> departureCity;

    @FXML
    private ComboBox<String> destinationCity;


    @FXML
    private CheckBox directRoutesOnly;


    ObservableList<String> citiesList= FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Repository<String, City> cityRepository = RepositoryFactory.getInstance().createRepository(RepositoryEntity.CITIES);
        Repository<String, TrainStation> trainStationRepository = RepositoryFactory.getInstance().createRepository(RepositoryEntity.TRAIN_STATIONS);
        Service service = new Service(cityRepository,trainStationRepository);

        for(City c :service.getAllCities()){
            citiesList.add(c.getName());
        }

        departureCity.setItems(citiesList);
        destinationCity.setItems(citiesList);
    }



    @FXML
    private ListView<String> ruteFiltrate;
    ObservableList<String> listaRute= FXCollections.observableArrayList();

    @FXML
    private Label mesajEroare;

    @FXML
    void afiseazaRute(MouseEvent event) {
        if(departureCity.getSelectionModel().getSelectedItem()== null || destinationCity.getSelectionModel().getSelectedItem()==null)
        {
            mesajEroare.setText("SELECT THE DEPARTURE CITY ANDE DESTINATION CITY");
        }
        else {
            listaRute.clear();
            Repository<String, City> cityRepository = RepositoryFactory.getInstance().createRepository(RepositoryEntity.CITIES);
            Repository<String, TrainStation> trainStationRepository = RepositoryFactory.getInstance().createRepository(RepositoryEntity.TRAIN_STATIONS);
            Service service = new Service(cityRepository,trainStationRepository);

            if(directRoutesOnly.isSelected()){// este bifat
                String idDepartureCity = service.getIdCityByName( departureCity.getSelectionModel().getSelectedItem());
                String idDestinationCity = service.getIdCityByName(destinationCity.getSelectionModel().getSelectedItem());
                // TRENURILE CE PORNESC DIN DEPARTURE_CITY
                List<String> trains = new ArrayList<String>();
                for(TrainStation t: service.getAllTrainStations()){
                    if(t.getDeparture_city().equals(idDepartureCity)){
                        String ruta_finala = service.connectedComponents(t.getId(),idDepartureCity,idDestinationCity) + ", price: ";
                        Long nr = ruta_finala.chars().filter(ch -> ch == '>').count();
                        ruta_finala += (nr+1)*t.getPrice_per_station();
                        listaRute.add(ruta_finala);
                    }
                }
                mesajEroare.setText("");

                ruteFiltrate.setItems(listaRute);

            }else{ // RUTE NEDIRECTE

            }
        }

    }
}
