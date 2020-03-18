package DAO;

import Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserDAO {

    public boolean isUserExist(String name);

    public List<User> getAllUsers();

    public void addUser(User user);

    public void deleteUser(String name);

    public void changeUserName(String name, String newName);

    public void changeUserPassword(String name, String password);

    public boolean validateUser(User user);
}
