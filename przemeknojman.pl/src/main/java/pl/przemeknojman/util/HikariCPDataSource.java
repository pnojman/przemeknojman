package pl.przemeknojman.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import pl.przemeknojman.dto.TestParametersDTO;

import java.sql.Connection;
import java.sql.SQLException;
public class HikariCPDataSource {

    private static volatile HikariCPDataSource instance;
    private final HikariDataSource dataSource;

    private HikariCPDataSource(TestParametersDTO testParameters){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://" + testParameters.getDbHost() + ":" + testParameters.getDbPort() + "/" +
                testParameters.getDbName());
        config.setUsername(testParameters.getDbUserName());
        config.setPassword(testParameters.getDbPassword());
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");

        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);
        config.setIdleTimeout(30000);
        config.setMaxLifetime(1800000);
        config.setConnectionTimeout(10000);

        dataSource = new HikariDataSource(config);
    }

    public static HikariCPDataSource getInstance(TestParametersDTO testParameters) {
        if (instance == null) {
            synchronized (HikariCPDataSource.class) {
                if (instance == null) {
                    instance = new HikariCPDataSource(testParameters);
                }
            }
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void close() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}
