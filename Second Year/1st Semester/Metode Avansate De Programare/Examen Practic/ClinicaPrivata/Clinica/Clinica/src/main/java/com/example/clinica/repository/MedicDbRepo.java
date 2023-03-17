package com.example.clinica.repository;

import com.example.clinica.domain.Medic;
import com.example.clinica.domain.Rezident;
import com.example.clinica.exceptions.InexistentEntityException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MedicDbRepo  implements Repository<Integer, Medic>{

    private String url;
    private String username;
    private String password;

    public MedicDbRepo(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    private Medic getMedicFromResultSet(ResultSet resultSet)throws SQLException {
        Integer id = resultSet.getInt("id");
        String nume = resultSet.getString("nume");
        Integer idSectie = resultSet.getInt("sectie");
        Integer vechime = resultSet.getInt("vechime");
        Rezident rezident = Rezident.valueOf(resultSet.getString("rezident")) ;
        return new Medic(id,nume,idSectie,vechime,rezident);
    }

    private boolean Exists(Integer id){
        String sql = "SELECT * FROM medici WHERE id = ?";
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
    public Medic find(Integer id) throws IllegalArgumentException {
        if(username == null) {
            throw new IllegalArgumentException("Id must be not null!");
        }

        String sql = "SELECT * FROM medici WHERE id = ?";

        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(!resultSet.next()) {
                throw new InexistentEntityException(id + " does not exist!");
            } else {
                return getMedicFromResultSet(resultSet);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return null;
    }

    @Override
    public Iterable<Medic> getAll() {
        List<Medic> medici = new ArrayList<>();
        String sql = "SELECT * FROM medici";

        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()) {
                Medic medic = getMedicFromResultSet(resultSet);
                medici.add(medic);
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return medici;
    }

    @Override
    public Map<Integer, Medic> getMap() {
        Map<Integer, Medic> medici = new HashMap<>();
        String sql = "SELECT * FROM medici";

        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()) {
                Medic medic = getMedicFromResultSet(resultSet);
                medici.put(medic.getId(), medic);
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return medici;
    }

    @Override
    public void clear() {
        String sql = "DELETE FROM medici";

        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public Medic save(Medic c) {
        return null;
    }
}
