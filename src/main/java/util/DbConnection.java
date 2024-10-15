package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static String url = "jdbc:postgresql://localhost:5432/postgres";
    private static String user = "postgres";
    private static String password = "postgres";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static void setConnectionDetails(String url, String username, String password) {
        DbConnection.url = url;
        DbConnection.user = username;
        DbConnection.password = password;
    }
}
