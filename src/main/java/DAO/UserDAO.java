package DAO;

import Models.User;
import Util.DBHelper;

import java.util.List;

public interface UserDAO {

    static UserDAO getJDBCDao(){
        return new UserJdbcDAO(DBHelper.getConnection());
    }

    static UserDAO getHibernateDAO(){
        return new UserHibernateDAO(DBHelper.getSessionFactory());
    }

    boolean isUserExist(String name);

    List<User> getAllUsers();

    void addUser(User user);

    void deleteUser(String name);

    void changeUserName(String name, String newName);

    void changeUserPassword(String name, String password);

    boolean validateUser(User user);
}
