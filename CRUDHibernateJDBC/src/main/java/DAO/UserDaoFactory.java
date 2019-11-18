package DAO;

import org.hibernate.SessionFactory;
import util.DBHelper;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class UserDaoFactory {
    private static UserDaoFactory userDaoFactory;

    private UserDaoFactory() {

    }

    public static UserDaoFactory getInstance() {
        if(userDaoFactory == null) {
            userDaoFactory = new UserDaoFactory();
        }
        return userDaoFactory;
    }

    public UserDao getUserDao() {
        Properties properties = new Properties();
        InputStream propertiesStream = UserDaoFactory.class.getClassLoader().getResourceAsStream("db.properties");
        String daoName = null;
        UserDao userDao = null;
        try {
                properties.load(propertiesStream);
                daoName = properties.getProperty("daoName");

            if (daoName.equalsIgnoreCase("hibernate")) {
                SessionFactory sessionFactory = DBHelper.getSessionFactory();
                userDao = UserDaoHibernateImpl.getInstance(sessionFactory);
                return userDao;
            } else {
                Connection connection = DBHelper.getConnection();
                userDao = new UserDaoJDBCimpl(connection);
            }
        } catch (IOException e) {
            System.out.println("File properties is empty");
        }
        return userDao;
    }
}
