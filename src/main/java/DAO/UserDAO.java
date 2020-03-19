package DAO;

import Models.User;
import Util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserDAO {

    public static UserDAO getJDBCDao(){
        return new UserDAOJdbc(DBHelper.getMysqlConnection());
    }

    public static UserDAO getHibernateDAO(){
        return new UserHibernateDAO(DBHelper.getSessionFactory());
    }

    public boolean isUserExist(String name);

    public List<User> getAllUsers();

    public void addUser(User user);

    public void deleteUser(String name);

    public void changeUserName(String name, String newName);

    public void changeUserPassword(String name, String password);

    public boolean validateUser(User user);
}
