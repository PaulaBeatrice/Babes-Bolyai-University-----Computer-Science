package com.example.model1.repos;

import com.example.model1.domains.Entity;
import com.example.model1.domains.SpecialOffer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class SpecialOfferRepo<ID, E extends Entity<ID>> implements Repository<Double, SpecialOffer> {

    private final JDBCUtils jdbcUtils = new JDBCUtils();

    @Override
    public SpecialOffer findEntity(Double aDouble) {
        return null;
    }

    @Override
    public List<SpecialOffer> findOne(Double aDouble, Date startDate, Date endDate) {
        List<SpecialOffer> list = new ArrayList<>();
        String query = "SELECT * from \"specialOffer\"";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Double specialOfferId = resultSet.getDouble("specialOfferId");
                Double hotelId = resultSet.getDouble("hotelId");
                Date sDate = resultSet.getDate("startDate");
                Date eDate = resultSet.getDate("endDate");
                Integer percents = resultSet.getInt("percents");
                if (Objects.equals(aDouble, hotelId) && Objects.equals(sDate, startDate) && Objects.equals(eDate, endDate)) {
                    SpecialOffer specialOffer = new SpecialOffer(specialOfferId, hotelId, startDate, endDate, percents);
                    list.add(specialOffer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Double findOneByName(String name) {
        return null;
    }

    @Override
    public Iterable<SpecialOffer> findAllWithName(String name) {
        return null;
    }

    @Override
    public SpecialOffer add(SpecialOffer entity) {
        return null;
    }


    @Override
    public List<String> getAll() {
        return null;
    }

    @Override
    public List<SpecialOffer> getAllEntitys() {
        return null;
    }

    @Override
    public List<SpecialOffer> offers(Date date, Integer fidelityGrade) {
        List<SpecialOffer> list = new ArrayList<>();
        String query = "SELECT * from \"specialOffer\"";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Double specialOfferId = resultSet.getDouble("specialOfferId");
                Double hotelId = resultSet.getDouble("hotelId");
                Date sDate = resultSet.getDate("startDate");
                Date eDate = resultSet.getDate("endDate");
                Integer percents = resultSet.getInt("percents");
                if (eDate.compareTo(date) > 0 && fidelityGrade > percents) {
                    SpecialOffer specialOffer = new SpecialOffer(specialOfferId, hotelId, sDate, eDate, percents);
                    list.add(specialOffer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
