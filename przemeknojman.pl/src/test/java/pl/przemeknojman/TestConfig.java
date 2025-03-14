package pl.przemeknojman;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import pl.przemeknojman.dto.TestParametersDTO;
import pl.przemeknojman.util.HikariCPDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class TestConfig {

    protected static TestParametersDTO testParameters = new TestParametersDTO();
    protected Connection connection;

    private static final Logger logger = LoggerFactory.getLogger(TestConfig.class);

    @BeforeSuite()
    @Parameters({"url", "browser", "apiUrl", "dbHost", "dbName", "dbPort", "dbUserName","dbPassword"})
    public void setUp(String url, String browser, String apiUrl, String dbHost, String dbName, int dbPort,
                      String dbUserName, String dbPassword) {

        testParameters.setUrl(url);
        testParameters.setBrowser(browser);
        testParameters.setApiUrl(apiUrl);
        testParameters.setDbHost(dbHost);
        testParameters.setDbName(dbName);
        testParameters.setDbPort(dbPort);
        testParameters.setDbUserName(dbUserName);
        testParameters.setDbPassword(dbPassword);
    }

    @AfterSuite
    public void afterSuite() {
        HikariCPDataSource.getInstance(testParameters).close();
    }

    protected void getDBConnection() {
        logger.info("Trying to connect to DB");
        try {
            if (connection == null || connection.isClosed()) {
                connection = HikariCPDataSource.getInstance(testParameters).getConnection();
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
