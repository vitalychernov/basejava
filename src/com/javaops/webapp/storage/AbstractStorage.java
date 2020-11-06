package com.javaops.webapp.storage;

import com.javaops.webapp.exception.ExistStorageException;
import com.javaops.webapp.exception.NotExistStorageException;
import com.javaops.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public Resume get(String uuid) {
        Object key = getKey(uuid);
        if (!keyExist(key)) {
            throw new NotExistStorageException(uuid);
        }
        return doGet(key);
    }

    public void update(Resume resume) {
        Object key = getKey(resume.getUuid());
        if (!keyExist(key)) {
            throw new NotExistStorageException(resume.getUuid());
        } else doUpdate(resume, key);
    }

    public void save(Resume resume) {
        Object key = getKey(resume.getUuid());
        if (keyExist(key)) {
            throw new ExistStorageException(resume.getUuid());
        } else doSave(resume, key);
    }

    public void delete(String uuid) {
        Object key = getKey(uuid);
        if (!keyExist(key)) {
            throw new NotExistStorageException(uuid);
        } else doDelete(key);
    }

    protected abstract void doDelete(Object key);

    protected abstract void doSave(Resume resume, Object key);

    protected abstract void doUpdate(Resume resume, Object key);

    protected abstract Resume doGet(Object key);

    protected abstract Object getKey(String uuid);

    protected abstract boolean keyExist(Object key);

}
