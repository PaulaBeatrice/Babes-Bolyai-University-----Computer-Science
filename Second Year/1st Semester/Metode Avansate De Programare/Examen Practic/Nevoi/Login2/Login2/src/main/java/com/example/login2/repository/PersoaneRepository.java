package com.example.login2.repository;

import com.example.login2.domain.Persoana;
import com.example.login2.domain.TipuriOrase;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PersoaneRepository implements Repository<Integer, Persoana> {
    private String url;
    private String username;
    private String password;

    public PersoaneRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Persoana find(Integer aDouble) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Persoana save(Persoana entity)
    {
        String sql = "INSERT INTO persoane(id, nume,prenume,username, parola,oras , strada, nr_strada, telefon) VALUES (?, ?, ?,?,?,?,?,?,?)";
        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getNume());
            statement.setString(3, entity.getPrenume());
            statement.setString(4, entity.getUsername());
            statement.setString(5, entity.getParola());
            statement.setString(6, String.valueOf(entity.getOras()));
            statement.setString(7, entity.getStrada());
            statement.setString(8, entity.getNr_strada());
            statement.setString(9, entity.getTelefon());
            //System.out.printf(statement.toString());

            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return entity;
    }

    @Override
    public Iterable<Persoana> getAll() {
        List<Persoana> bilete = new ArrayList<>();
        String sql = "SELECT * FROM persoane";

        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()) {
                Persoana rau = getPersoanaFromResult(resultSet);
                bilete.add(rau);
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return bilete;
    }

    private Persoana getPersoanaFromResult(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        String nume = resultSet.getString("nume");
        String prenume = resultSet.getString("prenume");
        String username = resultSet.getString("username");
        String parola = resultSet.getString("parola");
        TipuriOrase oras = TipuriOrase.valueOf(resultSet.getString("oras"));
        String strda = resultSet.getString("strada");
        String nr_strda = resultSet.getString("nr_strada");
        String telefon = resultSet.getString("telefon");
        return new Persoana(id,nume,prenume ,username,parola,oras,strda,nr_strda,telefon);
    }
    @Override
    public Map<Integer, Persoana> getMap() {
        return null;
    }
    @Override
    public void clear() {

    }

    @Override
    public Persoana update(Persoana entity) {
        return null;
    }
}
