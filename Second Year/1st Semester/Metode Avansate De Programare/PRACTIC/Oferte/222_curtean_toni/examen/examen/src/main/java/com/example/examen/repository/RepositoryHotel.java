package com.example.examen.repository;

import com.example.examen.domain.Hotel;
import com.example.examen.domain.Location;
import com.example.examen.domain.Type;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositoryHotel {

    List<Hotel> entities;
    private String username;
    private String password;
    private String url;

    public RepositoryHotel(String username, String password, String url) {
        this.username = username;
        this.password = password;
        this.url = url;
        this.entities=new ArrayList<>();
        loadData();
    }

    public void loadData() {
        this.entities.clear();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from hotels");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Double hotelId = resultSet.getDouble("id");
                Double locationId=resultSet.getDouble("location");
                String hotelName=resultSet.getString("name");
                Integer noRooms=resultSet.getInt("no_rooms");
                Double pricePerNight=resultSet.getDouble("price_night");
                Type type=Type.valueOf(resultSet.getString("type"));
                Hotel hotel=new Hotel(hotelId,locationId,hotelName,noRooms,pricePerNight,type);
                this.entities.add(hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Hotel> getEntities(){
        loadData();
        return this.entities;
    }
}
