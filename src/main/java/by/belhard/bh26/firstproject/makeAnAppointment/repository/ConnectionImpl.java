package by.belhard.bh26.firstproject.makeAnAppointment.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionImpl {
    private static final String url = "jdbc:mysql://localhost:3306/makeanappointment?serverTimezone=UTC";
    private static final String username = "root";
    private static final String password = "root";

    private static  Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static Connection getConnection() {
        return connection;
    }
}
