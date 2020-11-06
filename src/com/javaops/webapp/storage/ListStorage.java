package com.javaops.webapp.storage;

import com.javaops.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> storageList = new ArrayList<>();

    @Override
    public Resume doGet(Object index) {
        return storageList.get((Integer) index);
    }

    @Override
    public void doUpdate(Resume resume, Object index) {
        storageList.set((Integer) index, resume);
    }

    @Override
    public int size() {
        return storageList.size();
    }

    @Override
    public void clear() {
        storageList.clear();
    }

    @Override
    public void doSave(Resume resume, Object index) {
        storageList.add(resume);
    }

    @Override
    public void doDelete(Object index) {
        storageList.remove(((Integer) index).intValue());
    }

    @Override
    public Resume[] getAll() {
        return storageList.toArray(new Resume[storageList.size()]);
    }

    protected Integer getKey(String uuid) {
        for (int i = 0; i < storageList.size(); i++) {
            if (storageList.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean keyExist(Object key) {
        return key != null;
    }
}
