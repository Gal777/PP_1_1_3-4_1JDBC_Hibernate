package jm.task.core.jdbc.util;


import com.mysql.cj.xdevapi.SessionFactory;
import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;



public class Util {
    private static String url = "jdbc:mysql://localhost:3306/users";
    private static String user = "root";
    private static String password = "%murzilka@1976!";

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        SessionFactory sessionFactory = null;
        try {
            Properties settings = new Properties();
            settings.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/users");
            settings.setProperty("hibernate.connection.username", "root");
            settings.setProperty("hibernate.connection.password", "%murzilka@1976!");
            settings.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
            settings.setProperty("hibernate.hbm2ddl.auto", "create");
            Configuration configuration = new Configuration();
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = (SessionFactory) configuration.buildSessionFactory(serviceRegistry);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }




    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("соединение установлено");
        } catch (SQLException e) {
            System.out.println("Соединение не установлено!");
        }
        return connection;
    }

}





