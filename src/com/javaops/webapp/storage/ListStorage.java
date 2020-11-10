package com.javaops.webapp.storage;

import com.javaops.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    private List<Resume> storageList = new ArrayList<>();

    @Override
    public Resume doGet(Integer searchKey) {
        return storageList.get(searchKey);
    }

    @Override
    public void doUpdate(Resume resume, Integer searchKey) {
        storageList.set(searchKey, resume);
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
    public void doSave(Resume resume, Integer searchKey) {
        storageList.add(resume);
    }

    @Override
    public void doDelete(Integer searchKey) {
        storageList.remove(searchKey.intValue());
    }

    @Override
    protected List<Resume> doGetAll() {
        return new ArrayList<>(storageList);
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
    protected boolean isExist(Integer searchKey) {
        return searchKey != null;
    }
}
