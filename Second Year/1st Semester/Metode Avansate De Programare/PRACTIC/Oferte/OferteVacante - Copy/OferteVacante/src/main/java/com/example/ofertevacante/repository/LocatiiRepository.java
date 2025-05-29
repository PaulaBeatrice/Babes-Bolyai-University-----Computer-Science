package com.example.ofertevacante.repository;

import com.example.ofertevacante.domain.Location;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LocatiiRepository implements Repository<Double, Location> {
    private String url;
    private String username;
    private String password;

    public LocatiiRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Location find(Double aDouble) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Location save(Location entity) {
        return null;
    }

    @Override
    public Iterable<Location> getAll() {
        List<Location> locatii = new ArrayList<>();
        String sql = "SELECT * FROM locatii";

        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()) {
                Location locatie = getLocatieFromResult(resultSet);
                locatii.add(locatie);
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return locatii;
    }

    private Location getLocatieFromResult(ResultSet resultSet) throws SQLException {
        Double id = resultSet.getDouble("id");
        String locationName = resultSet.getString("location_name");
        return new Location(id, locationName);
    }

    @Override
    public Map<Double, Location> getMap() {
        return null;
    }

    @Override
    public void clear() {

    }
}
