package DAO;

import Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOJdbc implements UserDAO {

    public Connection connection;

    public UserDAOJdbc(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean isUserExist(String name) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE name = ?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean result = resultSet.next();
            preparedStatement.close();
            resultSet.close();
            return result;
        } catch (SQLException exc){
            return false;
        }
    }

    public boolean validateUser(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE name = ? and password = ?");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean result = resultSet.next();
            preparedStatement.close();
            resultSet.close();
            return result;
        } catch (SQLException exc){
            return false;
        }
    }

    @Override
    public List<User> getAllUsers() {
        try {
            ResultSet resultSet = connection.prepareStatement("SELECT * FROM users").executeQuery();
            List<User> users = new ArrayList<User>();
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
            }
            resultSet.close();
            connection.close();
            return users;
        } catch (SQLException exc) {
            exc.printStackTrace();
            return null;
        }
    }

    @Override
    public void addUser(User user) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (name, password) values (?, ?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
            connection.commit();
            preparedStatement.close();
            connection.close();
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public void deleteUser(String name) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE name = ?");
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.commit();
            connection.close();
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public void changeUserName(String name, String newName) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET name = ? WHERE name = ?");
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.commit();
            connection.close();
        } catch (SQLException exc) {
            exc.printStackTrace();
        }

    }

    @Override
    public void changeUserPassword(String name, String password) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET password = ? WHERE name = ?");
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.commit();
            connection.close();
        } catch (SQLException exc){
            exc.printStackTrace();
        }
    }
}
