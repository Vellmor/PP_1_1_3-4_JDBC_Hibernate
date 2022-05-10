package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        UserDao userDaoJDBC = new UserDaoJDBCImpl();
        UserDao userDaoHibernate = new UserDaoHibernateImpl();

//        userDaoHibernate.createUsersTable();
        userDaoJDBC.createUsersTable();

//        userDaoJDBC.saveUser("Alex", "Lion", (byte) 20);
        userDaoHibernate.saveUser("Alex", "Lion", (byte) 20);
        userDaoJDBC.saveUser("Marti", "Zebra", (byte) 25);
        userDaoJDBC.saveUser("Melman", "Giraffe", (byte) 31);
        userDaoJDBC.saveUser("Gloria", "Hippo", (byte) 38);

        userDaoJDBC.removeUserById(1);
        userDaoJDBC.getAllUsers();
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();
    }
}
