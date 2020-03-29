package DAO;

import Models.User;
import Util.DBHelper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO {

    private DBHelper dbHelper = DBHelper.getDBHelper();

    public UserJdbcDAO() {
    }

    @Override
    public boolean isUserExist(String name) {
        try (Connection connection = dbHelper.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE name = ?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean result = resultSet.next();
            preparedStatement.close();
            resultSet.close();
            return result;
        } catch (SQLException exc) {
            return false;
        }
    }

    @Override
    public boolean validateUser(String name, String password) {
        try (Connection connection = dbHelper.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE name = ? and password = ?");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean result = resultSet.next();
            preparedStatement.close();
            resultSet.close();
            return result;
        } catch (SQLException exc) {
            return false;
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Connection connection = dbHelper.getConnection()) {
            ResultSet resultSet = connection.prepareStatement("SELECT * FROM users").executeQuery();
            List<User> users = new ArrayList<User>();
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
            }
            resultSet.close();
            return users;
        } catch (SQLException exc) {
            exc.printStackTrace();
            return null;
        }
    }

    @Override
    public void addUser(User user) {
        try (Connection connection = dbHelper.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (name, password, role) values (?, ?, ?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.executeUpdate();
            connection.commit();
            preparedStatement.close();
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public void deleteUser(String name) {
        try (Connection connection = dbHelper.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE name = ?");
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.commit();
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public void changeUserName(String name, String newName) {
        try (Connection connection = dbHelper.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET name = ? WHERE name = ?");
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.commit();
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public void changeUserPassword(String name, String password) {
        try (Connection connection = dbHelper.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET password = ? WHERE name = ?");
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.commit();
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public String getRole(String name, String password) {
        String role = null;
        try (Connection connection = dbHelper.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT role from users where name =? and password = ?");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            role = resultSet.getString(1);
            connection.commit();
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return role;
    }

    @Override
    public User getUserByName(String name) {
        User user = null;
        try (Connection connection = dbHelper.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from users where name = ?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user = new User(resultSet.getString("name"), resultSet.getString("password"), resultSet.getString("role"));
            connection.commit();
            resultSet.close();

        } catch (SQLException exc){
            exc.printStackTrace();
        }
        return user;
    }
}