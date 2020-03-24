package Util;

import Models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBHelper {

    private static DBHelper dbHelper;

    private DBHelper(){}

    public static DBHelper getDBHelper(){
        if (dbHelper == null){
            dbHelper = new DBHelper();
        }
        return dbHelper;
    }

    public Session getSession() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.setProperty("hibernate.dialect", ApplicationConfig.getInstance().getHibernateDialect());
        configuration.setProperty("hibernate.connection.driver_class", ApplicationConfig.getInstance().getHibernateConnectionDriverClass());
        configuration.setProperty("hibernate.connection.url", ApplicationConfig.getInstance().getHibernateConnectionUrl());
        configuration.setProperty("hibernate.connection.username", ApplicationConfig.getInstance().getHibernateConnectionUsername());
        configuration.setProperty("hibernate.connection.password", ApplicationConfig.getInstance().getHibernateConnectionPassword());
        configuration.setProperty("hibernate.show_sql", ApplicationConfig.getInstance().getHibernateShowSql());
        configuration.setProperty("hibernate.hbm2ddl.auto", ApplicationConfig.getInstance().getHibernateHbm2ddlAuto());
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry).openSession();
    }

    public Connection getConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("mydbtest?").          //db name
                    append("user=admin&").          //login
                    append("password=admin").    //password
                    append("&serverTimezone=UTC");   //setup server time")

            System.out.println("URL: " + url + "\n");

            return DriverManager.getConnection(url.toString());
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }
}
