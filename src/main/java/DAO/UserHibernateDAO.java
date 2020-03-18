package DAO;

import Models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class UserHibernateDAO implements UserDAO {

    Session session;

    public UserHibernateDAO (Session session){
        this.session = session;
    }

    @Override
    public boolean isUserExist(String name) {
        Transaction transaction = session.beginTransaction();
        Query<User> query = session.createQuery("FROM User WHERE name = ?", User.class);
        query.setParameter(0, name);
        User user = query.uniqueResult();
        transaction.commit();
        return user != null;
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("FROM User", User.class).list();
        transaction.commit();
        session.close();
        return users;
    }

    @Override
    public void addUser(User user) {
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteUser(String name) {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE User WHERE name = ?");
        query.setParameter(0, name);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void changeUserName(String name, String newName) {
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
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("FROM User where name =? and password = ?", User.class).list();
        boolean result = users.size() > 0;
        transaction.commit();
        session.close();
        return result;
    }
}
