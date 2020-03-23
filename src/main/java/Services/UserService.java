package Services;

import DAO.UserDAO;
import Models.User;
import factories.UserDAOFactory;

import java.util.List;

public class UserService {

    private static  UserService userService;

    private UserDAO userDAO = UserDAOFactory.getUserDao();

    private UserService() {}

    public static UserService getInstance(){
        if (userService == null){
            userService = new UserService();
        }
        return userService;
    }

    public List<User> getAllUsers(){
        return userDAO.getAllUsers();
    }

    public boolean addUser(User user) {
        boolean result = false;
        if(!userDAO.isUserExist(user.getName())){
            userDAO.addUser(user);
            result = true;
        }
        return result;
    }

    public void deleteUser(String name) {
        userDAO.deleteUser(name);
    }

    public void changeUserName(String name, String newName) {
        userDAO.changeUserName(name, newName);
    }

    public void changeUserPassword(String name, String password) {
        userDAO.changeUserPassword(name, password);
    }

    public boolean validateUser(User user){
        return userDAO.validateUser(user);
    }

    public boolean isUserExist(String name) {
        return userDAO.isUserExist(name);
    }
}
