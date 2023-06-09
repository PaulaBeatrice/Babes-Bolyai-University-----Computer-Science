package repository.database;


import domain.User;
import domain.validator.UserValidator;
import repository.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class UserDBRepo implements Repository<User, Long> {

    private final JDBCUtils jdbcUtils = new JDBCUtils();
    private UserValidator validator;

    public UserDBRepo(UserValidator validator) {
        this.validator = validator;
    }

    @Override
    public User findOne(Long aLong) {
        String query = "SELECT * FROM users WHERE \"Id\" = ?";
        User user1   = null;
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setLong(1, aLong);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                user1 = new User(firstName, lastName);
                user1.setId(aLong);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user1;
    }

    @Override
    public Iterable<User> findAll() {
        Set<User> usersSet = new HashSet<>();
        try(Connection connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/network_lab", "postgres", "postgress");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = preparedStatement.executeQuery()){
            while(resultSet.next()){
                Long ID = resultSet.getLong("Id");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                User user = new User(firstName, lastName);
                user.setId(ID);
                usersSet.add(user);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return usersSet;
    }

    @Override
    public User save(User entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }
        validator.validate(entity);

        String query = "INSERT INTO users(\"Id\",\"FirstName\",\"LastName\") VALUES (?,?,?)";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setLong(1, entity.getId());
            statement.setString(2, entity.getFirstName());
            statement.setString(3, entity.getLastName());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return entity;
    }

    @Override
    public User delete(Long aLong) {
        String query = "DELETE FROM users WHERE \"Id\" = ?";
        User user = findOne(aLong);
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setLong(1, aLong);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public User update(User entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }
        validator.validate(entity);
        if(findOne(entity.getId()) == null)
            throw new IllegalArgumentException("The entity does not exist!");

        String query = "UPDATE users SET \"FirstName\" = ?,\"LastName\" = ? WHERE \"Id\" = ?";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setLong(3, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }
}
