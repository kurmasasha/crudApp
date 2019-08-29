package ru.kurma.dao;

import ru.kurma.model.User;
import ru.kurma.util.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImplJDBC implements UserDao {
    @Override
    public List<User> findAllUsers() {
        List<User> userList = new ArrayList<>();
        try {
            ResultSet resultSet = DBConnector.getInstance().getConnection().createStatement().executeQuery("SELECT * FROM testdb.test.users");
            while (resultSet.next()) {
                userList.add(completeUser(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public User findUserById(Integer id) {
        try {
            PreparedStatement preparedStatement = DBConnector.getInstance().getConnection().prepareStatement("SELECT * FROM testdb.test.users WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return completeUser(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User findUserByLogin(String login) {
        try {
            PreparedStatement preparedStatement = DBConnector.getInstance().getConnection().prepareStatement("SELECT * FROM testdb.test.users WHERE login = ?");
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return completeUser(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void createNewUser(String firstName, String lastName, String login, String password, String role) {
        try {
            PreparedStatement preparedStatement = DBConnector.getInstance().getConnection().prepareStatement
                    ("INSERT INTO testdb.test.users(firstname, lastname, login, password, role) VALUES (?,?,?,?,?)");
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, login);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, role);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        try {
            PreparedStatement preparedStatement = DBConnector.getInstance().getConnection().prepareStatement("UPDATE testdb.test.users SET firstName = ?, lastname = ? WHERE id = ?");
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setInt(3, user.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(Integer id) {
        try {
            PreparedStatement preparedStatement = DBConnector.getInstance().getConnection().prepareStatement("DELETE FROM testdb.test.users WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private User completeUser(ResultSet resultSet) {
        try {
            return new User(
                    resultSet.getInt("id"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getString("login"),
                    resultSet.getString("password"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
