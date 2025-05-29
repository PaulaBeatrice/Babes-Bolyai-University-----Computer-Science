package com.example.examen.repository;

import com.example.examen.domain.City;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CityRepo implements Repository<String, City>{
    private String url;
    private String username;
    private String password;

    public CityRepo(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    private City getCityFromResultSet(ResultSet resultSet)throws SQLException {
        String id = resultSet.getString("id");
        String name = resultSet.getString("name");
        return new City(id,name);
    }


    @Override
    public Iterable<City> getAll() {
        List<City> cities = new ArrayList<>();
        String sql = "SELECT * FROM cities";

        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()) {
                City city = getCityFromResultSet(resultSet);
                cities.add(city);
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return cities;
    }


}
