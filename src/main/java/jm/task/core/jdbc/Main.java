package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDao userDaoJDBC = new UserDaoJDBCImpl();

        userDaoJDBC.createUsersTable();

        userDaoJDBC.saveUser("Alex", "Lion", (byte) 20);
        userDaoJDBC.saveUser("Marti", "Zebra", (byte) 25);
        userDaoJDBC.saveUser("Melman", "Giraffe", (byte) 31);
        userDaoJDBC.saveUser("Gloria", "Hippo", (byte) 38);

        userDaoJDBC.removeUserById(1);
        List<User> usersJdbc = userDaoJDBC.getAllUsers();
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();


        UserDao userDaoHibernate = new UserDaoHibernateImpl();

        userDaoHibernate.createUsersTable();

        userDaoHibernate.saveUser("Alex", "Lion", (byte) 20);
        userDaoHibernate.saveUser("Marti", "Zebra", (byte) 25);
        userDaoHibernate.saveUser("Melman", "Giraffe", (byte) 31);
        userDaoHibernate.saveUser("Gloria", "Hippo", (byte) 38);

        userDaoHibernate.removeUserById(61);
        List<User> usersHibernate = userDaoHibernate.getAllUsers();
        userDaoHibernate.cleanUsersTable();
        userDaoHibernate.dropUsersTable();
    }
}
