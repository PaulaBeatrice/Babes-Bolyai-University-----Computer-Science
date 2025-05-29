package com.example.model1.repos;

import com.example.model1.domains.Entity;
import com.example.model1.domains.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationRepo<ID, E extends Entity<ID>> implements Repository<Double, Reservation> {
    private final JDBCUtils jdbcUtils = new JDBCUtils();

    @Override
    public Reservation findEntity(Double aDouble) {
        return null;
    }

    @Override
    public List<Reservation> findOne(Double aDouble, Date startDate, Date endDate) {
        return null;
    }

    @Override
    public Double findOneByName(String name) {
        return null;
    }

    @Override
    public Iterable<Reservation> findAllWithName(String name) {
        return null;
    }

    @Override
    public Reservation add(Reservation entity) {
        String query = "INSERT INTO \"rezervations\"(\"rezervationId\", \"clientId\", \"hotelId\", \"startDate\", \"noNights\") VALUES (?,?,?,?,?) ";
        try (Connection connection = jdbcUtils.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, entity.getReservationId());
            statement.setDouble(2, entity.getClientId());
            statement.setDouble(3, entity.getHotelId());
            statement.setDate(4, (java.sql.Date) entity.getStartDate());
            statement.setInt(5, entity.getNumberOfNights());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    @Override
    public List<String> getAll() {
        return null;
    }

    @Override
    public List<Reservation> getAllEntitys() {
        List<Reservation> list = new ArrayList<>();
        String query = "SELECT * from users";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Double reservationId = resultSet.getDouble("rezervationId");
                Double clientId = resultSet.getDouble("clientId");
                Double hotelId = resultSet.getDouble("hotelId");
                Date startDate = resultSet.getDate("startDate");
                Integer nNights = resultSet.getInt("noNights");
                Reservation reservation = new Reservation(reservationId, clientId, hotelId, startDate, nNights);
                list.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Reservation> offers(Date date, Integer fidelityGrade) {
        return null;
    }
}
