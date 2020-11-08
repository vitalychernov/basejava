package com.javaops.webapp.storage;

import com.javaops.webapp.exception.ExistStorageException;
import com.javaops.webapp.exception.NotExistStorageException;
import com.javaops.webapp.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    private Object keyNotExistStorage(String uuid) {
        Object key = getKey(uuid);
        if (keyExist(key))
            throw new ExistStorageException(uuid);
        return key;
    }

    private Object keyExistStorage(String uuid) {
        Object key = getKey(uuid);
        if (!keyExist(key))
            throw new NotExistStorageException(uuid);
        return key;
    }

    public Resume get(String uuid) {
        Object key = keyExistStorage(uuid);
        return doGet(key);
    }

    public void update(Resume resume) {
        Object key = keyExistStorage(resume.getUuid());
        doUpdate(resume, key);
    }

    public void save(Resume resume) {
        Object key = keyNotExistStorage(resume.getUuid());
        doSave(resume, key);
    }

    public void delete(String uuid) {
        Object key = keyExistStorage(uuid);
        doDelete(key);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = doGetAll();
        Collections.sort(list);
        return list;
    }

    protected abstract List<Resume> doGetAll();

    protected abstract void doDelete(Object key);

    protected abstract void doSave(Resume resume, Object key);

    protected abstract void doUpdate(Resume resume, Object key);

    protected abstract Resume doGet(Object key);

    protected abstract Object getKey(String uuid);

    protected abstract boolean keyExist(Object key);

}
