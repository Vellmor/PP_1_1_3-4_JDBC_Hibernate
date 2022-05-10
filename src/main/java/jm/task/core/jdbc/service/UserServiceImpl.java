package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDaoJDBCImpl userDaoJdbc = new UserDaoJDBCImpl();
    private final UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();

    public void createUsersTable() {
//        userDaoJdbc.createUsersTable();
        userDaoHibernate.createUsersTable();
    }

    public void dropUsersTable() {
//        userDaoJdbc.dropUsersTable();
        userDaoHibernate.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
//        userDaoJdbc.saveUser(name, lastName, age);
        userDaoHibernate.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userDaoJdbc.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userDaoJdbc.getAllUsers();
    }

    public void cleanUsersTable() {
        userDaoJdbc.cleanUsersTable();
    }
}
