package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoHibernateImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoJDBCImpl.class.getCanonicalName());
    private final Session session = Util.getSessionFactory().openSession();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try {
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users");
            session.getTransaction().commit();
            session.flush();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            session.flush();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            session.saveOrUpdate(new User(name, lastName, age));
            session.flush();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        Util.getSessionFactory().getCurrentSession().find(User.class, id);
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
