package com.javaops.webapp.storage;

import com.javaops.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> storageMap = new HashMap<>();

    @Override
    protected void doDelete(Object key) {
        storageMap.remove((String) key);
    }

    @Override
    protected void doSave(Resume resume, Object key) {
        storageMap.put((String) key, resume);
    }

    @Override
    protected void doUpdate(Resume resume, Object key) {
        storageMap.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(Object key) {
        return storageMap.get((String) key);
    }

    @Override
    protected Object getKey(String key) {
        return key;
    }

    @Override
    protected boolean keyExist(Object key) {
        return storageMap.containsKey((String) key);
    }

    @Override
    public void clear() {
        storageMap.clear();
    }

    @Override
    public Resume[] getAll() {
        return storageMap.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storageMap.size();
    }
}