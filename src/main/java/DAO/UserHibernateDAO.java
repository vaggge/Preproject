package DAO;

import Models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class UserHibernateDAO implements UserDAO {

    SessionFactory sessionFactory;

    public UserHibernateDAO (SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean isUserExist(String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<User> query = session.createQuery("FROM User WHERE name = ?", User.class);
        query.setParameter(0, name);
        User user = query.uniqueResult();
        transaction.commit();
        return user != null;
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("FROM User", User.class).list();
        transaction.commit();
        session.close();
        return users;
    }

    @Override
    public void addUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteUser(String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE User WHERE name = ?");
        query.setParameter(0, name);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void changeUserName(String name, String newName) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("UPDATE User SET name = ? WHERE name = ?");
        query.setParameter(0, newName);
        query.setParameter(1, name);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void changeUserPassword(String name, String password) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("UPDATE User SET password = ? WHERE name = ?");
        query.setParameter(0, password);
        query.setParameter(1, name);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public boolean validateUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("FROM User where name =? and password = ?", User.class).list();
        boolean result = users.size() > 0;
        transaction.commit();
        session.close();
        return result;
    }
}
