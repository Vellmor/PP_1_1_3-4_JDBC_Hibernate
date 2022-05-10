package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {
    private static final Logger logger = Logger.getLogger(Util.class.getCanonicalName());
    private static final String URL = "jdbc:mysql://localhost:3306/db";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public static Connection getJdbcDbConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Не удалось установить соединение с БД: " + e);
            return null;
        }
    }

    public static Connection getHibernateDbConnection() {
        return null;
    }



    public static SessionFactory getSessionFactory() {
        //реализация синглтона. Если объекта нет - создаем, если есть просто возвращаем
        if (sessionFactory == null) {
            try {
                StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

                //стандартные настройки для хибернат
                //для тех, кто использует другую базу данных нужно заметить поле DRIVER, DIALECT и кусок URL легко гуглятся под любую базу
                Map<String, String> settings = new HashMap<>();
                settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/db");
                settings.put(Environment.USER, "user");
                settings.put(Environment.PASS, "password");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");

                registryBuilder.applySettings(settings);

                registry = registryBuilder.build();

                MetadataSources sources = new MetadataSources(registry);
                sources.addAnnotatedClass(User.class);
                Metadata metadata = sources.getMetadataBuilder().build();

                sessionFactory = metadata.getSessionFactoryBuilder().build();

            } catch (Exception e) {
                e.printStackTrace();
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;
    }

    public static void close() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

}
