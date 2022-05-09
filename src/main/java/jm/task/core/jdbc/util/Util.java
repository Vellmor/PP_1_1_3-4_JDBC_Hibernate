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
    private static final String URL = "jdbc:mysql://localhost:3307/db";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public static Connection getJdbcDbConnection() {
        try {
//            return DriverManager.getConnection(URL, USER, PASSWORD);
            return DriverManager.getConnection("jdbc:h2:mem:mydb", "sa", "password");
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
                settings.put(Environment.DRIVER, "org.h2.Driver");
                settings.put(Environment.URL, "jdbc:h2:mem:mydb");
                settings.put(Environment.USER, "sa");
                settings.put(Environment.PASS, "password");
                settings.put(Environment.DIALECT, ">org.hibernate.dialect.H2Dialect");

//                settings.put(Environment.DRIVER, "org.postgresql.Driver");
//                settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/hibernate_tutorial");
//                settings.put(Environment.USER, "postgres");
//                settings.put(Environment.PASS, "postgres");
//                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL9Dialect");

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
