package com.javaops.webapp.storage;

import com.javaops.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> storageMap = new HashMap<>();


    @Override
    protected void doDelete(Integer index) {

    }

    @Override
    protected void doSave(Resume resume, Integer index) {

    }

    @Override
    protected void doUpdate(Resume resume, Integer index) {

    }

    @Override
    protected Resume doGet(Integer index) {
        return null;
    }

    @Override
    protected int getIndex(String uuid) {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public int size() {
        return 0;
    }
}
