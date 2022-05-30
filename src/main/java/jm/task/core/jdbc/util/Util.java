package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Util {
    private static String url = "jdbc:mysql://localhost:3306/users";
    private static String user = "root";
    private static String password = "%murzilka@1976!";
    public static Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("соединение установлено");
        } catch (SQLException e) {
            System.out.println("Соединение не установлено!");
        } return connection;
    }
}
