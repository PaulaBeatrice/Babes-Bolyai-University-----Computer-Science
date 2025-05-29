package com.example.model1.repos;

import com.example.model1.domains.Entity;
import com.example.model1.domains.Hotel;
import com.example.model1.domains.Location;
import com.example.model1.domains.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class HotelRepo<ID, E extends Entity<ID>> implements Repository<Double, Hotel> {

    private final JDBCUtils jdbcUtils = new JDBCUtils();

    @Override
    public Hotel findEntity(Double aDouble) {
        String query = "SELECT * FROM hotels";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Double hotelId = resultSet.getDouble("hotelId");
                Double LocationId = resultSet.getDouble("locationId");
                String hotelName = resultSet.getString("hotelName");
                Integer noRooms = resultSet.getInt("noRooms");
                Double pricePerNight = resultSet.getDouble("pricePerNight");
                String type = resultSet.getString("type");
                if (Objects.equals(aDouble, hotelId)) {
                    Hotel hotel = new Hotel(hotelId, LocationId, hotelName, noRooms, pricePerNight, Type.valueOf(type));
                    return hotel;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Hotel> findOne(Double aDouble, Date startDate, Date endDate) {
        return null;
    }

    @Override
    public Double findOneByName(String name) {
        String query = "SELECT * FROM hotels";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Double hotelId = resultSet.getDouble("hotelId");
                Double LocationId = resultSet.getDouble("locationId");
                String hotelName = resultSet.getString("hotelName");
                Integer noRooms = resultSet.getInt("noRooms");
                Double pricePerNight = resultSet.getDouble("pricePerNight");
                String type = resultSet.getString("type");
                if (Objects.equals(name, hotelName)) {
                    // Hotel hotel = new Hotel(hotelId, LocationId, hotelName, noRooms, pricePerNight, Type.valueOf(type));
                    return hotelId;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Iterable<Hotel> findAllWithName(String name) {
        List<Hotel> list = new ArrayList<>();
        locationRepo<Double, Location> locationRepo = new locationRepo<>();
        Double locationId = locationRepo.findOneByName(name);
        String query = "SELECT * FROM hotels";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Double hotelId = resultSet.getDouble("hotelId");
                Double LocationId = resultSet.getDouble("locationId");
                String hotelName = resultSet.getString("hotelName");
                Integer noRooms = resultSet.getInt("noRooms");
                Double pricePerNight = resultSet.getDouble("pricePerNight");
                String type = resultSet.getString("type");
                if (Objects.equals(locationId, LocationId)) {
                    Hotel hotel = new Hotel(hotelId, LocationId, hotelName, noRooms, pricePerNight, Type.valueOf(type));
                    list.add(hotel);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Hotel add(Hotel entity) {
        return null;
    }


    @Override
    public List<String> getAll() {
        return null;
    }

    @Override
    public List<Hotel> getAllEntitys() {
        return null;
    }

    @Override
    public List<Hotel> offers(Date date, Integer fidelityGrade) {
        return null;
    }
}
