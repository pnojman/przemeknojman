package pl.przemeknojman.dao;

import pl.przemeknojman.dto.JbnaActionLogsExtensionsDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class JbnaActionLogsExtensionsDAO implements BaseDAO<JbnaActionLogsExtensionsDTO> {

    private final Connection connection;

    public JbnaActionLogsExtensionsDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(JbnaActionLogsExtensionsDTO entity) {
        String query = "INSERT INTO jbna_action_logs_extensions(`extension`) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getExtension());
            statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    @Override
    public void update(JbnaActionLogsExtensionsDTO entity) {

        String query = "UPDATE jbna_action_logs_extensions SET extension = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getExtension());
            statement.setLong(2, entity.getId());
            statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    @Override
    public void delete(int id) {

        String query = "DELETE FROM jbna_action_logs_extensions WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }

    }

    public Optional<JbnaActionLogsExtensionsDTO> selectByField(String fieldName, Object value) {
        String query = "SELECT * FROM jbna_action_logs_extensions WHERE " + fieldName + " = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, value);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                JbnaActionLogsExtensionsDTO jbnaActionLogsExtensions = new JbnaActionLogsExtensionsDTO(
                        resultSet.getInt("id"),
                        resultSet.getString("extension")
                );
                return Optional.of(jbnaActionLogsExtensions);
            }
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
        return Optional.empty();
    }
}
