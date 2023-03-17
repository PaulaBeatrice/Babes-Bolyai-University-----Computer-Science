package com.example.clinica.repository;

import com.example.clinica.domain.Sectie;
import com.example.clinica.exceptions.InexistentEntityException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.*;

public class SectieDbRepo implements Repository<Integer, Sectie>{

    private String url;
    private String username;
    private String password;

    public SectieDbRepo(String url, String username, String password) {
        this.url = "jdbc:postgresql://localhost:5432/clinica";
        this.username = "postgres";
        this.password = "postgress";
    }

    private Sectie getSectieFromResultSet(ResultSet resultSet)throws SQLException{
        Integer id = resultSet.getInt("id");
        String nume = resultSet.getString("nume");
        Integer idSef = resultSet.getInt("sef");
        Integer pret = resultSet.getInt("pret");
        Integer durata = resultSet.getInt("durata");
        return new Sectie(id,nume,idSef,pret,durata);
    }

    private boolean Exists(Integer id){
        String sql = "SELECT * FROM sectii WHERE id = ?";
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
    public Sectie find(Integer id) throws IllegalArgumentException {
        if(username == null) {
            throw new IllegalArgumentException("Id must be not null!");
        }

        String sql = "SELECT * FROM sectii WHERE id = ?";

        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(!resultSet.next()) {
                throw new InexistentEntityException(id + " does not exist!");
            } else {
                return getSectieFromResultSet(resultSet);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return null;
    }

    @Override
    public Iterable<Sectie> getAll() {
        List<Sectie> sectii = new ArrayList<>();
        String sql = "SELECT * FROM sectii";

        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()) {
                Sectie sectie = getSectieFromResultSet(resultSet);
                sectii.add(sectie);
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return sectii;
    }

    @Override
    public Map<Integer, Sectie> getMap() {
        Map<Integer, Sectie> sectii = new HashMap<>();
        String sql = "SELECT * FROM sectii";

        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()) {
                Sectie sectie = getSectieFromResultSet(resultSet);
                sectii.put(sectie.getId(), sectie);
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return sectii;
    }

    @Override
    public void clear() {
        String sql = "DELETE FROM sectii";

        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public Sectie save(Sectie c) {
        return null;
    }
}
