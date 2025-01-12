package pl.przemeknojman.dao;

import java.sql.SQLException;
import java.util.Optional;

public interface BaseDAO<T> {

    void insert(T entity) throws SQLException;
    void update(T entity) throws SQLException;
    void delete(int id) throws SQLException;
    Optional<T> selectByField(String fieldName, Object value) throws SQLException;
}
