package com.example.model1.repos;

import com.example.model1.domains.Entity;
import com.example.model1.domains.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class locationRepo<ID, E extends Entity<ID>> implements Repository<Double, Location> {

    private final JDBCUtils jdbcUtils = new JDBCUtils();


    @Override
    public Location findEntity(Double aDouble) {
        String query = "SELECT * from location";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Double locationId = resultSet.getDouble("locationId");
                String locationName = resultSet.getString("locationName");
                if (Objects.equals(aDouble, locationId)) {
                    Location location = new Location(locationId, locationName);
                    return location;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Location> findOne(Double aDouble, Date startDate, Date endDate) {
        return null;
    }

    @Override
    public Double findOneByName(String name) {
        String query = "SELECT * from location";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Double locationId = resultSet.getDouble("locationId");
                String locationName = resultSet.getString("locationName");
                if (Objects.equals(name, locationName)) {
                    return locationId;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Iterable<Location> findAllWithName(String name) {
        return null;
    }

    @Override
    public Location add(Location entity) {
        return null;
    }

    @Override
    public List<String> getAll() {
        List<String> list = new ArrayList<>();
        String query = "SELECT * from location";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Double locationId = resultSet.getDouble("locationId");
                String locationName = resultSet.getString("locationName");
                list.add(locationName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Location> getAllEntitys() {
        return null;
    }

    @Override
    public List<Location> offers(Date date, Integer fidelityGrade) {
        return null;
    }
}
