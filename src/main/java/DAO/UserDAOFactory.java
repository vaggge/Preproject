package DAO;

import DAO.UserDAO;
import DAO.UserHibernateDAO;
import DAO.UserJdbcDAO;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/*Если файле application.properties прописано значение "JDBC", то используется реализация UserJdbcDao, если значение
"Hibernate", то используется реализация UserHibernateDAO
  */

public class UserDAOFactory {

    public static UserDAO getUserDao() throws IllegalArgumentException, IOException {
        Properties properties = new Properties();
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties")) {
            properties.load(in);
            String nameOfDao = properties.getProperty("daotype");
            if (nameOfDao.contains("JDBC")) {
                return new UserJdbcDAO();
            } else if (nameOfDao.contains("Hibernate")) {
                return new UserHibernateDAO();
            } else {
                throw new IllegalArgumentException("Required configuration parameter 'daotype' is not provided'");
            }
        }
    }
}
