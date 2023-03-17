package com.example.zboruri.repository;

import com.example.zboruri.domain.Client;
import com.example.zboruri.domain.Ticket;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TicketRepo implements Repository<Integer, Ticket> {
    private String url;
    private String username;
    private String password;

    public TicketRepo(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    private Ticket getTicketFromResultSet(ResultSet resultSet)throws SQLException {
        Integer id = resultSet.getInt("id");
        String username = resultSet.getString("username");
        Long zborId = resultSet.getLong("id_zbor");
        LocalDateTime purchase_data = resultSet.getTimestamp("purchase_data").toLocalDateTime();

        return new Ticket(id,username,zborId,purchase_data);
    }

    @Override
    public Ticket find(Integer integer) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Iterable<Ticket> getAll() {
        List<Ticket> tickete = new ArrayList<>();
        String sql = "SELECT * FROM tickete";

        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()) {
                Ticket ticket = getTicketFromResultSet(resultSet);
                tickete.add(ticket);
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return tickete;
    }

    @Override
    public Map<Integer, Ticket> getMap() {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public Ticket save(Ticket c) {
        String sql = "INSERT INTO tickete(id, username, id_zbor, purchase_data) VALUES (?, ?, ?,?)";

        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, c.getId());
            statement.setString(2, c.getUsername());
            statement.setLong(3, c.getId_zbor());
            statement.setTimestamp(4, Timestamp.valueOf(c.getPurchase_data()));


            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return c;
    }

    @Override
    public Ticket update(Ticket entity) {
        return null;
    }
}
