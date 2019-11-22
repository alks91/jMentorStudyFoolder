package DAO;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("from User", User.class).list();
        transaction.commit();
        session.close();
        return users;
    }
    @Override
    public boolean isExistUser(String email) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from model.User where email=:email", User.class);
        query.setParameter("email", email);
        return query.list().isEmpty();
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
    public void updateEmailById(Long id, String email) {
        Session session = sessionFactory.openSession();
        User user = getUserById(id);
        user.setEmail(email);
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    @Override
    public User getUserByEmail(String email) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from model.User where email=:email", User.class);
        query.setParameter("email", email);
        List<User> list = query.list();
        transaction.commit();
        session.close();
            return list.get(0);
    }
    @Override
    public boolean isAuthUser(String email, String password) {
        User user = null;
        if(!isExistUser(email)) {
            user = getUserByEmail(email);
        }
        return user.getPassword().equals(password);
    }

}
