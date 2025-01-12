package pl.przemeknojman.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariCPDataSource {

    private static volatile HikariCPDataSource instance;
    private final HikariDataSource dataSource;

    private HikariCPDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://mariadb105.server122739.nazwa.pl:3306/server122739_nation");
        config.setUsername("server122739_nation");
        config.setPassword("Sobacz1566");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");

        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);
        config.setIdleTimeout(30000);
        config.setMaxLifetime(1800000);
        config.setConnectionTimeout(10000);

        dataSource = new HikariDataSource(config);
    }

    public static HikariCPDataSource getInstance() {
        if (instance == null) {
            synchronized (HikariCPDataSource.class) {
                if (instance == null) {
                    instance = new HikariCPDataSource();
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
