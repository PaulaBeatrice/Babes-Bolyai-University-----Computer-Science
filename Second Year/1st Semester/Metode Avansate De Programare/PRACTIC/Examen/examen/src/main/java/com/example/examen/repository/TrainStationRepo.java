package com.example.examen.repository;

import com.example.examen.domain.City;
import com.example.examen.domain.TrainStation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TrainStationRepo implements Repository<String , TrainStation> {
    private String url;
    private String username;
    private String password;

    public TrainStationRepo(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    private TrainStation getTrainsStationFromResultSet(ResultSet resultSet)throws SQLException {
        String id = resultSet.getString("id");
        String departure_city_id = resultSet.getString("departure_city");
        String destination_city_id = resultSet.getString("destination_city");
        Float price_per_station = resultSet.getFloat("price");
        return new TrainStation(id,departure_city_id,destination_city_id,price_per_station);
    }

    @Override
    public Iterable<TrainStation> getAll() {
        List<TrainStation> train_stations = new ArrayList<>();
        String sql = "SELECT * FROM trainstations";

        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()) {
                TrainStation trainstation = getTrainsStationFromResultSet(resultSet);
                train_stations.add(trainstation);
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return train_stations;
    }

}
