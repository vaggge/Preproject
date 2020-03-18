package Services;

import DAO.UserDAO;
import DAO.UserDAOimpl;
import DAO.UserHibernateDAO;
import Models.User;
import Util.DBHelper;
import org.hibernate.SessionFactory;
import Util.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private static UserService userService;

    private SessionFactory sessionFactory;

    private UserService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService(DBHelper.getSessionFactory());
        }
        return userService;
    }

    public List<User> getAllUsers(){
        return new UserHibernateDAO(sessionFactory.openSession()).getAllUsers();
    }

    public boolean addUser(User user) {
        UserDAO userDAO = new UserHibernateDAO(sessionFactory.openSession());
        boolean result = false;
        if(!userDAO.isUserExist(user.getName())){
            userDAO.addUser(user);
            result = true;
        }
        return result;
    }

    public void deleteUser(String name) {
        UserDAO userDAO = new UserHibernateDAO(sessionFactory.openSession());
        userDAO.deleteUser(name);
    }

    public void changeUserName(String name, String newName) {
        UserDAO userDAO = new UserHibernateDAO(sessionFactory.openSession());
        userDAO.changeUserName(name, newName);
    }

    public void changeUserPassword(String name, String password) {
        UserDAO userDAO = new UserHibernateDAO(sessionFactory.openSession());
        userDAO.changeUserPassword(name, password);
    }

    public boolean validateUser(User user){
        return new UserHibernateDAO(sessionFactory.openSession()).validateUser(user);
    }

    public boolean isUserExist(String name) {
        UserDAO userDAO = new UserHibernateDAO(sessionFactory.openSession());
        return userDAO.isUserExist(name);
    }
}
