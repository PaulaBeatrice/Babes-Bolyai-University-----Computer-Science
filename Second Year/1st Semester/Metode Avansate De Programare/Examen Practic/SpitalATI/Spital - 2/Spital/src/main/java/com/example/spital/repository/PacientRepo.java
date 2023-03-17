package com.example.spital.repository;

import com.example.spital.domain.Pacient;
import com.example.spital.domain.Pat;
import com.example.spital.domain.TipPat;
import com.example.spital.domain.YesNo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PacientRepo implements Repository<String, Pacient> {
    private String url;
    private String username;
    private String password;

    public PacientRepo(String url, String username, String password) {
        this.url = "jdbc:postgresql://localhost:5432/spital";
        this.username = "postgres";
        this.password = "postgress";
    }

    private Pacient getPacientFromResultSet(ResultSet resultSet)throws SQLException {
        String cnp = resultSet.getString("cnp");
        int varsta = resultSet.getInt("varsta");
        YesNo prematur = YesNo.valueOf(resultSet.getString("prematur"));
        String diagnostic = resultSet.getString("diagnostic");
        int gravitate = resultSet.getInt("gravitate");
        return new Pacient(cnp,varsta,prematur,diagnostic,gravitate);
    }
    @Override
    public Pacient find(String s) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Iterable<Pacient> getAll() {
        List<Pacient> pacienti = new ArrayList<>();
        String sql = "SELECT * FROM pacienti";

        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()) {
                Pacient pacient = getPacientFromResultSet(resultSet);
                pacienti.add(pacient);
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return pacienti;
    }

    @Override
    public Map<String, Pacient> getMap() {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public Pacient save(Pacient c) {
        return null;
    }

    @Override
    public void update(String val, Integer id) {

    }
}
