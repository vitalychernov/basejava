package com.javaops.webapp.storage;

import com.javaops.webapp.ResumeTestData;
import com.javaops.webapp.exception.ExistStorageException;
import com.javaops.webapp.exception.NotExistStorageException;
import com.javaops.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class AbstractStorageTest {

    protected Storage storage;

    private final static String UUID_1 = "uuid1";
    private final static String UUID_2 = "uuid2";
    private final static String UUID_3 = "uuid3";
    private final static String UUID_4 = "uuid4";

    private final static Resume RESUME_1 = ResumeTestData.create(UUID_1, "Григорий Кислин");
    private final static Resume RESUME_2 = new Resume(UUID_2, "Name2");
    private final static Resume RESUME_3 = new Resume(UUID_3, "Name3");
    private final static Resume RESUME_4 = new Resume(UUID_4, "Name4");

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
        Resume r1 = new Resume(UUID_1, "New Name");
        storage.update(r1);
        Assert.assertSame(r1, storage.get(UUID_1));
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
        storage.save(new Resume(UUID_4, "Name4"));
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