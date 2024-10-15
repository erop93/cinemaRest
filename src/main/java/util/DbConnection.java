package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {
    private static final String PROPERTIES_FILE = "application.properties";
    private static String url;
    private static String user;
    private static String password;

    static {
        try (InputStream input = DbConnection.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            Properties props = new Properties();
            props.load(input);

            url = props.getProperty("url");
            user = props.getProperty("user");
            password = props.getProperty("password");
        } catch (IOException e) {
            throw new RuntimeException("Unable to download properties", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static void setConnectionDetails(String url, String username, String password) {
        DbConnection.url = url;
        DbConnection.user = username;
        DbConnection.password = password;
    }
}
