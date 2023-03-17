package com.example.ofertevacante.repository;

import com.example.ofertevacante.domain.Hotel;
import com.example.ofertevacante.domain.Location;
import com.example.ofertevacante.domain.typeHotel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HotelRepository implements Repository<Double, Hotel> {
    private String url;
    private String username;
    private String password;

    public HotelRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Hotel find(Double aDouble) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Hotel save(Hotel entity) {
        return null;
    }

    @Override
    public Iterable<Hotel> getAll() {
        List<Hotel> hoteluri = new ArrayList<>();
        String sql = "SELECT * FROM hoteluri";

        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()) {
                Hotel locatie = getHotelFromResult(resultSet);
                hoteluri.add(locatie);
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return hoteluri;
    }

    private Hotel getHotelFromResult(ResultSet resultSet) throws SQLException {
        Double id = resultSet.getDouble("id");
        Double location_id = resultSet.getDouble("location_id");
        String name = resultSet.getString("name");
        Integer nr_camere = resultSet.getInt("nr_camere");
        Double pret = resultSet.getDouble("pret");
        typeHotel type = typeHotel.valueOf(resultSet.getString("type"));
        return new Hotel(id, location_id,name,nr_camere,pret,type);
    }

    @Override
    public Map<Double, Hotel> getMap() {
        return null;
    }

    @Override
    public void clear() {

    }
}
