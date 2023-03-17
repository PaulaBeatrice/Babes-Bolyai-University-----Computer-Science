package com.example.clinica.repository;

import com.example.clinica.domain.Consultatie;
import com.example.clinica.domain.Medic;
import com.example.clinica.domain.Rezident;
import com.example.clinica.exceptions.InexistentEntityException;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsultatieDbRepo implements Repository<Integer, Consultatie> {
    private String url;
    private String username;
    private String password;

    public ConsultatieDbRepo(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    private Consultatie getConsultatieFromResultSet(ResultSet resultSet)throws SQLException {
        Integer id = resultSet.getInt("id");
        Integer idMedic = resultSet.getInt("medic");
        String cnpP = resultSet.getString("cnp");
        String numaPacient = resultSet.getString("pacient");
        LocalDateTime data = resultSet.getTimestamp("data").toLocalDateTime();
        Integer ora = resultSet.getInt("ora");
        return new Consultatie(id,idMedic,cnpP,numaPacient,data,ora);
    }

    private boolean Exists(Integer id){
        String sql = "SELECT * FROM consultatii WHERE id = ?";
        try(Connection connection = DriverManager.getConnection(this.url,this.username,this.password);
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return  true;
            }
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return false;
    }


    @Override
    public Consultatie find(Integer id) throws IllegalArgumentException {
        if(username == null) {
            throw new IllegalArgumentException("Id must be not null!");
        }

        String sql = "SELECT * FROM consultatii WHERE id = ?";

        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(!resultSet.next()) {
                throw new InexistentEntityException(id + " does not exist!");
            } else {
                return getConsultatieFromResultSet(resultSet);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return null;
    }

    @Override
    public Iterable<Consultatie> getAll() {
        List<Consultatie> consultatii = new ArrayList<>();
        String sql = "SELECT * FROM consultatii";

        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()) {
                Consultatie consultatie = getConsultatieFromResultSet(resultSet);
                consultatii.add(consultatie);
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return consultatii;
    }

    @Override
    public Map<Integer, Consultatie> getMap() {
        Map<Integer, Consultatie> consulatii = new HashMap<>();
        String sql = "SELECT * FROM consultatii";

        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()) {
                Consultatie consultatie = getConsultatieFromResultSet(resultSet);
                consulatii.put(consultatie.getId(), consultatie);
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return consulatii;
    }

    @Override
    public void clear() {
        String sql = "DELETE FROM consultatii";

        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

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
}
