package com.javaops.webapp.sql;

import com.javaops.webapp.exception.ExistStorageException;
import com.javaops.webapp.exception.StorageException;
import org.postgresql.util.PSQLException;

import java.sql.SQLException;

public class StorageSqlException {
    public static StorageException executeSqlException(SQLException e) {
        if (e instanceof PSQLException) {
            if (e.getSQLState().equals("23505")) {
                return new ExistStorageException("Resume already exists");
            }
        }
        return new StorageException(e);
    }
}
