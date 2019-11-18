package util;

import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import static util.PropertyReader.getPropertyValue;

import java.sql.*;

public class DBHelper {

   private static SessionFactory sessionFactory;
   private DBHelper() {

   }
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }

    @SuppressWarnings("UnusedDeclaration")
    private static Configuration getMySqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", getPropertyValue("hibernate.dialect"));
        configuration.setProperty("hibernate.connection.driver_class", getPropertyValue("hibernate.connection.driver_class"));
        configuration.setProperty("hibernate.connection.url", getPropertyValue("hibernate.connection.url"));
        configuration.setProperty("hibernate.connection.username", getPropertyValue("hibernate.connection.username"));
        configuration.setProperty("hibernate.connection.password", getPropertyValue("hibernate.connection.password"));
        configuration.setProperty("hibernate.show_sql", getPropertyValue("hibernate.show_sql"));
        return configuration;
    }

    private static SessionFactory createSessionFactory() {
        Configuration configuration = getMySqlConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }




    public static Connection getConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());

            Connection connection = DriverManager.getConnection(getPropertyValue("jdbc.connection.url"));
            System.out.println(getPropertyValue("jdbc.connection.url"));
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }
}
