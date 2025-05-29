package com.example.examen.repository;

import com.example.examen.domain.Location;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositoryLocation {

    List<Location> entities;
    private String username;
    private String password;
    private String url;

    public RepositoryLocation(String username, String password, String url) {
        this.username = username;
        this.password = password;
        this.url = url;
        this.entities=new ArrayList<>();
        loadData();
    }

    public void loadData() {
        this.entities.clear();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from locations");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Double locationId = resultSet.getDouble("id");
                String locationName=resultSet.getString("name");
                Location location=new Location(locationId,locationName);
                this.entities.add(location);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Location> getEntities(){
        loadData();
        return this.entities;
    }

}
