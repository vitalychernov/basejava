package com.javaops.webapp.storage;

import com.javaops.webapp.exception.StorageException;
import com.javaops.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public Resume doGet(Object index) {
        return storage[(Integer) index];
    }

    public void doUpdate(Resume resume, Object index) {
        storage[(Integer) index] = resume;
    }

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void doSave(Resume resume, Object index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            saveElement(resume, (Integer) index);
            size++;
        }
    }

    public void doDelete(Object index) {
        deleteElement((Integer) index);
        storage[size - 1] = null;
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    protected List<Resume> doGetAll() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    abstract void saveElement(Resume resume, int index);

    abstract void deleteElement(int index);

    protected abstract Integer getKey(String uuid);

    @Override
    protected boolean keyExist(Object index) {
        return (Integer) index >= 0;
    }
}
