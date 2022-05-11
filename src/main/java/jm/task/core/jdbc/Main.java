package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceHibernateImpl;
import jm.task.core.jdbc.service.UserServiceJdbcImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceJdbcImpl();
//        UserService userService = new UserServiceHibernateImpl();

        userService.createUsersTable();

        userService.saveUser("Alex", "Lion", (byte) 20);
        userService.saveUser("Marti", "Zebra", (byte) 25);
        userService.saveUser("Melman", "Giraffe", (byte) 31);
        userService.saveUser("Gloria", "Hippo", (byte) 38);

        userService.removeUserById(1);
        List<User> users = userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
