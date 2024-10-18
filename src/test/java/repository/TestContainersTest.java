package repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import util.DbConnection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;


/**
 * Базовый класс с testcontainers, от которого будут наследоваться другие тесты.
 */
@Testcontainers
public abstract class TestContainersTest {

    private static final String PROPERTIES_FILE = "test.properties";
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

    /**
     * Данные для подключения к БД Постгрес.
     */
    @Container
    public PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>("postgres:17.0")
            .withDatabaseName(url)
            .withUsername(user)
            .withPassword(password);

    /**
     * Запуск Постгрес контейнера перед каждым тестом.
     */
    @BeforeEach
    public void setUp() {
        postgreSQLContainer.start();
        DbConnection.setConnectionDetails(
                postgreSQLContainer.getDatabaseName(),
                postgreSQLContainer.getUsername(),
                postgreSQLContainer.getPassword()
        );
    }

    /**
     * Очистка БД после каждого теста и отключение от Постгрес контейнера.
     */
    @AfterEach
    public void tearDown() throws SQLException {
        try (Connection connection = DbConnection.getConnection()) {
            String[] tables = {"actors", "movies", "genres"};

            for (String table : tables) {
                try (PreparedStatement preparedStatement = connection.prepareStatement("TRUNCATE TABLE " + table + " RESTART IDENTITY CASCADE")) {
                    preparedStatement.executeUpdate();
                }
            }
        }
        postgreSQLContainer.stop();
    }
}
