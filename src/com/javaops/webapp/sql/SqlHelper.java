package com.javaops.webapp.sql;

import com.javaops.webapp.exception.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void execute(String sql) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    public <T> T execute(String sql, ABlockOfCode<T> aBlockOfCode) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            return aBlockOfCode.executeSpecific(ps);
        } catch (SQLException e) {
            throw StorageSqlException.executeSqlException(e);
        }
    }

    @FunctionalInterface
    public interface ABlockOfCode<T> {
        T executeSpecific(PreparedStatement ps) throws SQLException;
    }
}
