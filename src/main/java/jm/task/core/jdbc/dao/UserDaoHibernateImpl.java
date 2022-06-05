package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    UserDao userDao = new UserDaoHibernateImpl ();
    public UserDaoHibernateImpl() {
      
    }

    private SessionFactory sessionFactory;
    Session session = sessionFactory.openSession();
    Transaction trans = null;



    @Override
    public void createUsersTable() {
    try {
        trans = session.beginTransaction();
    session.createSQLQuery("CREATE TABLE IF NOT EXISTS users "+
        "(id BIGINT PRIMARY KEY AUTO_INCREMENT,name VARCHAR(255), surname VARCHAR(255), age INT)").executeUpdate();
    trans.commit();
    } catch (Exception e) {
        if (trans != null) trans.rollback();
        e.printStackTrace();
    } finally {
        session.close();
    }

    }

    @Override
    public void dropUsersTable() {
    try {
        trans = session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
        trans.commit();
    } catch (Exception e) {
        if (trans != null) trans.rollback();
        e.printStackTrace();
    } finally {
        session.close();
    }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            trans = session.beginTransaction();
            session.save("INSERT INTO users (name, surname, age) VALUES (:name, :surname, :age)");
            trans.commit();
        } catch (Exception e) {
            if (trans != null) trans.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    @Override
    public void removeUserById(int id) {
    try {
        trans = session.beginTransaction();
       session.createSQLQuery("DELETE FROM users WHERE id = ?").executeUpdate();
    } catch (Exception e) {
        if (trans != null)
            trans.rollback();
        e.printStackTrace();
    } finally {
        session.close();
    }

    }

    @Override
    public List<User> getAllUsers() {
        List <User> users = new ArrayList<>();
        try {
            trans = session.beginTransaction();
            CriteriaQuery<User> criteriaQuery = session.getCriteriaBuilder().createQuery(User.class);
            criteriaQuery.from(User.class);
            return users;
        } catch (Exception e) {
            if (trans != null)
                trans.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return users;
    }

    @Override
    public void cleanUsersTable() {
            try {
                trans = session.beginTransaction();
                session.createSQLQuery("TRUNCATE TABLE users").executeUpdate();
            } catch (Exception e) {
                if (trans != null) trans.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }
    }
}
