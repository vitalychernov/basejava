package com.javaops.webapp.storage;

import com.javaops.webapp.Config;
import com.javaops.webapp.ResumeTestData;
import com.javaops.webapp.exception.ExistStorageException;
import com.javaops.webapp.exception.NotExistStorageException;
import com.javaops.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class AbstractStorageTest {

    protected final static File STORAGE_DIR = Config.get().getStorageDir();

    protected Storage storage;

    private final static String UUID_1 = ResumeTestData.U1;
    private final static String UUID_2 = ResumeTestData.U2;
    private final static String UUID_3 = ResumeTestData.U3;
    private final static String UUID_4 = ResumeTestData.U4;

    private final static Resume RESUME_1 = ResumeTestData.R1;
    private final static Resume RESUME_2 = ResumeTestData.R2;
    private final static Resume RESUME_3 = ResumeTestData.R3;
    private final static Resume RESUME_4 = ResumeTestData.R4;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void get() {
        Assert.assertEquals(RESUME_1, storage.get(UUID_1));
        Assert.assertEquals(RESUME_2, storage.get(UUID_2));
        Assert.assertEquals(RESUME_3, storage.get(UUID_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_1, "New Name");
        storage.update(newResume);
        Assert.assertEquals(newResume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_4);
    }

    @Test
    public void clear() {
        Assert.assertEquals(3, storage.size());
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void save() {
        Assert.assertEquals(3, storage.size());
        storage.save(RESUME_4);
        Assert.assertEquals(RESUME_4, storage.get(UUID_4));
        Assert.assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        Assert.assertEquals(3, storage.size());
        storage.delete(UUID_3);
        Assert.assertEquals(2, storage.size());
        storage.get(UUID_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_4);
    }

    @Test
    public void getAllSorted() {
        List<Resume> list = storage.getAllSorted();
        Assert.assertEquals(storage.size(), list.size());
        List<Resume> resumesSorted = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);
        Collections.sort(resumesSorted);
        Assert.assertEquals(resumesSorted, list);
    }
}