package com.example.zboruri.repository;

import com.example.zboruri.domain.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.sql.*;

public class ClientiRepo implements Repository<Integer, Client> {
    private String url;
    private String username;
    private String password;

    public ClientiRepo(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    private Client getClientFromResultSet(ResultSet resultSet)throws SQLException {
        Integer id = resultSet.getInt("id");
        String nume = resultSet.getString("nume");
        String username = resultSet.getString("username");

        return new Client(id,nume,username);
    }



    @Override
    public Client find(Integer integer) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Iterable<Client> getAll() {
        List<Client> clienti = new ArrayList<>();
        String sql = "SELECT * FROM clienti";

        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()) {
                Client client = getClientFromResultSet(resultSet);
                clienti.add(client);
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return clienti;
    }

    @Override
    public Map<Integer, Client> getMap() {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public Client save(Client c) {
        return null;
    }

    @Override
    public Client update(Client entity) {
        return null;
    }
}

/*
    public Consultatie save(Consultatie entity){
        String sql = "INSERT INTO consultatii(id, medic, cnp, pacient, data,ora) VALUES (?, ?, ?,?,?,?)";

        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, entity.getId());
            statement.setInt(2, entity.getIdMedic());
            statement.setString(3, entity.getCNPPacient());
            statement.setString(4, entity.getNumePacient());
            statement.setTimestamp(5, Timestamp.valueOf(entity.getData()));
            statement.setInt(6, entity.getOra());

            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return entity;
    }

 */