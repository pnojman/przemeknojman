package pl.przemeknojman;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import pl.przemeknojman.util.HikariCPDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class TestConfig {

    protected static String apiUrl;
    protected static String url;
    protected static String browser;
    protected Connection connection;

    private static final Logger logger = LoggerFactory.getLogger(TestConfig.class);

    @BeforeSuite()
    @Parameters({"url", "browser", "apiUrl"})
    public void setUp(String url, String browser, String apiUrl) {
        TestConfig.url = url;
        TestConfig.browser = browser;
        TestConfig.apiUrl = apiUrl;
    }

    protected void getDBConnection() {
        logger.info("Trying to connect to DB");
        try {
            if (connection == null || connection.isClosed()) {
                connection = HikariCPDataSource.getInstance().getConnection();
                logger.debug("Connection is established" + connection);
            }
        } catch (SQLException sqlException) {
            logger.error(sqlException.toString());
            throw new RuntimeException(sqlException);
        }
    }

    protected void closeDbConnection() {
        try {
            if (!connection.isClosed())
                connection.close();
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }
}
