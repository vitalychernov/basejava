package com.javaops.webapp.storage;

import com.javaops.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage {

    private Map<String, Resume> storageMap = new HashMap<>();

    @Override
    protected void doDelete(Object key) {
        storageMap.remove(((Resume) key).getUuid());
    }

    @Override
    protected void doSave(Resume resume, Object key) {
        storageMap.put(resume.getUuid(), resume);
    }

    @Override
    protected void doUpdate(Resume resume, Object key) {
        storageMap.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(Object key) {
        return (Resume) key;
    }

    @Override
    protected Object getKey(String key) {
        return storageMap.get(key);
    }

    @Override
    protected boolean keyExist(Object key) {
        return key != null;
    }

    @Override
    public void clear() {
        storageMap.clear();
    }

    @Override
    protected List<Resume> doGetAll() {
        return new ArrayList<>(storageMap.values());
    }

    @Override
    public int size() {
        return storageMap.size();
    }
}