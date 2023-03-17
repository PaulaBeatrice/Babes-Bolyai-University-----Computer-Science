package com.example.zboruri.repository;

import com.example.zboruri.domain.Client;
import com.example.zboruri.domain.Zbor;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ZborRepo implements Repository<Long, Zbor> {
    private String url;
    private String username;
    private String password;

    public ZborRepo(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    private Zbor getZborFromResultSet(ResultSet resultSet)throws SQLException {
        Long id = resultSet.getLong("id");
        String from = resultSet.getString("from");
        String to = resultSet.getString("to");
        LocalDateTime depTime = resultSet.getTimestamp("dep_time").toLocalDateTime();
        LocalDateTime landTime = resultSet.getTimestamp("land_time").toLocalDateTime();
        Integer seats = resultSet.getInt("seats");
        return new Zbor(id,from,to,depTime,landTime,seats);
    }

    @Override
    public Zbor find(Long aLong) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Iterable<Zbor> getAll() {
        List<Zbor> zboruri = new ArrayList<>();
        String sql = "SELECT * FROM zboruri";

        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()) {
                Zbor zbor = getZborFromResultSet(resultSet);
                zboruri.add(zbor);
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return zboruri;
    }

    @Override
    public Map<Long, Zbor> getMap() {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public Zbor save(Zbor c) {
        return null;
    }

    @Override
    public Zbor update(Zbor entity) {
        String sql = "UPDATE zboruri SET seats = ? WHERE id = ?";

        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, entity.getSeats());
            statement.setLong(2, entity.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }
}
