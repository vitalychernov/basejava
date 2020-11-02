package com.javaops.webapp.storage;

import com.javaops.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> storageList = new ArrayList<>();

    @Override
    public Resume doGet(Integer index) {
        return storageList.get(index);
    }

    @Override
    public void doUpdate(Resume resume, Integer index) {
        storageList.set(index, resume);
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
    public void doSave(Resume resume, Integer index) {
        storageList.add(resume);
    }

    @Override
    public void doDelete(Integer index) {
        storageList.remove(storageList.get(index));
    }

    @Override
    public Resume[] getAll() {
        return storageList.toArray(new Resume[storageList.size()]);
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < storageList.size(); i++) {
            if (storageList.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
