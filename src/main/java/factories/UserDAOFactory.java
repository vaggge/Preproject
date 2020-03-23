package factories;

import DAO.UserDAO;
import DAO.UserHibernateDAO;
import DAO.UserJdbcDAO;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserDAOFactory {

    public static UserDAO getUserDao() {
        Properties properties = new Properties();
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("data.properties")) {
            properties.load(in);
            String nameOfDao = properties.getProperty("daotype");
            if (nameOfDao.contains("JDBC")) {
                return new UserJdbcDAO();
            } else if (nameOfDao.contains("Hibernate")) {
                return new UserHibernateDAO();
            }
            return null;
        } catch (IOException exc) {
            exc.printStackTrace();
            return null;
        }
    }
}
