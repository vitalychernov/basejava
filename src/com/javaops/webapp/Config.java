package com.javaops.webapp;

import com.javaops.webapp.storage.SqlStorage;
import com.javaops.webapp.storage.Storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public class Config {

    private final static File PROPS = new File("config\\resumes.properties");
    private final static Config INSTANCE = new Config();
    private final File STORAGE_DIR;
    private final Storage STORAGE;

    public static Config get() {
        return INSTANCE;
    }

    public File getStorageDir() {
        return STORAGE_DIR;
    }

    public Storage getStorage() {
        return STORAGE;
    }

    private Config() {
        try (InputStream is = new FileInputStream(PROPS)) {
            Properties props = new Properties();
            props.load(is);
            STORAGE_DIR = new File(props.getProperty("storage.dir"));
            STORAGE = new SqlStorage(props.getProperty("db.url"),
                    props.getProperty("db.user"),
                    props.getProperty("db.password"));
        } catch (IOException | SQLException e) {
            throw new IllegalArgumentException("Invalid config file " + PROPS.getAbsolutePath());
        }
    }
}