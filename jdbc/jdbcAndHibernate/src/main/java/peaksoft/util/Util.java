package peaksoft.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String LOGIN = "postgres";
    private static final String PASSWORD = "Tvrandot";

    public static Connection jdbcUtilConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("Успешно подключен к PostgreSQL. \n");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

}

