package com.example.ofertevacante.repository;

import com.example.ofertevacante.domain.Hotel;
import com.example.ofertevacante.domain.Reservation;
import com.example.ofertevacante.domain.typeHotel;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReservationRepository implements Repository<Double, Reservation>{
    private String url;
    private String username;
    private String password;

    public ReservationRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    private Reservation getReservationFromResult(ResultSet resultSet) throws SQLException {
        Double id = resultSet.getDouble("id");
        Long id_client = resultSet.getLong("id_client");
        Double id_hotel = resultSet.getDouble("id_hotel");
        LocalDateTime date = resultSet.getTimestamp("start_date").toLocalDateTime();
        Integer nr_night = resultSet.getInt("no_nights");

        return new Reservation(id,id_client,id_hotel,date,nr_night);
    }



    @Override
    public Reservation find(Double aDouble) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Reservation save(Reservation entity) {
        String sql = "INSERT INTO reservations(id, id_client, id_hotel, start_date, no_nights) VALUES (?, ?, ?,?,?)";

        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setDouble(1, entity.getId());
            statement.setLong(2, entity.getId_client());
            statement.setDouble(3, entity.getId_hotel());
            statement.setTimestamp(4, Timestamp.valueOf(entity.getStart_date()));
            statement.setInt(5, entity.getNr_nights());

            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return entity;
    }

    @Override
    public Iterable<Reservation> getAll() {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations";

        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()) {
                Reservation reservation = getReservationFromResult(resultSet);
                reservations.add(reservation);
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return reservations;
    }

    @Override
    public Map<Double, Reservation> getMap() {
        return null;
    }

    @Override
    public void clear() {

    }
}
