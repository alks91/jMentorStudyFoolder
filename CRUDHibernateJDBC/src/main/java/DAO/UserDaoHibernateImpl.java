package DAO;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao{

    private static UserDaoHibernateImpl userDaoHibernate;
    private SessionFactory sessionFactory;

    static UserDaoHibernateImpl getInstance(SessionFactory sessionFactory) {
        if(userDaoHibernate == null) {
            userDaoHibernate = new UserDaoHibernateImpl(sessionFactory);
        }
        return userDaoHibernate;
    }

    private UserDaoHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User getUserById(Long id) {
        Session session = sessionFactory.openSession();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }
    @Override
    public List<User> getAllUser() {
        Session session = sessionFactory.openSession();
        Transaction transaction =session.beginTransaction();
        List<User> users = session.createQuery("from model.User").list();
        transaction.commit();
        session.close();
        return users;
    }
    @Override
    public boolean addUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
        return true;
    }
    @Override
    public void deleteUserById(Long id) {
        Session session = sessionFactory.openSession();
        User user = getUserById(id);
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void updateNameById(Long id, String name) {
        Session session = sessionFactory.openSession();
        User user = getUserById(id);
        user.setName(name);
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }
}
