package Util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationConfig {

    private static ApplicationConfig applicationConfig;

    private final String hibernateDialect = getProperty().getProperty("hibernate.dialect");
    private final String hibernateConnectionDriverClass = getProperty().getProperty("hibernate.connection.driver_class");
    private final String hibernateConnectionUrl = getProperty().getProperty("hibernate.connection.url");
    private final String hibernateConnectionUsername = getProperty().getProperty("hibernate.connection.username");
    private final String hibernateConnectionPassword = getProperty().getProperty("hibernate.connection.password");
    private final String hibernateShowSql = getProperty().getProperty("hibernate.show_sql");
    private final String hibernateHbm2ddlAuto = getProperty().getProperty("hibernate.hbm2ddl.auto");

    private ApplicationConfig() {}

    public static ApplicationConfig getInstance(){
        if(applicationConfig == null){
            applicationConfig = new ApplicationConfig();
        }
        return applicationConfig;
    }

    private Properties getProperty() {
        Properties properties = new Properties();
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties")) {
            properties.load(in);
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        return properties;
    }

    public String getHibernateDialect() {
        return hibernateDialect;
    }

    public String getHibernateConnectionDriverClass() {
        return hibernateConnectionDriverClass;
    }

    public String getHibernateConnectionUrl() {
        return hibernateConnectionUrl;
    }

    public String getHibernateConnectionUsername() {
        return hibernateConnectionUsername;
    }

    public String getHibernateConnectionPassword() {
        return hibernateConnectionPassword;
    }

    public String getHibernateShowSql() {
        return hibernateShowSql;
    }

    public String getHibernateHbm2ddlAuto() {
        return hibernateHbm2ddlAuto;
    }
}