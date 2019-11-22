package DAO;

import org.hibernate.SessionFactory;
import util.DBHelper;
import static util.PropertyReader.getPropertyValue;
import java.sql.Connection;


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
            if (getPropertyValue("daoName").equalsIgnoreCase("hibernate")) {
                SessionFactory sessionFactory = DBHelper.getSessionFactory();
                return UserDaoHibernateImpl.getInstance(sessionFactory);
            } else {
                Connection connection = DBHelper.getConnection();
                return UserDaoJDBCimpl.getInstance(connection);
            }
    }
}
