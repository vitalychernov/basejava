package com.javaops.webapp.storage;

import com.javaops.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListStorage extends AbstractStorage {

    List<Resume> storageList = new ArrayList<>();

    @Override
    public Resume doGet(String uuid) {
        return storageList.get(index);
    }

    @Override
    public void doUpdate(Resume resume) {
        resume = storageList.get(index);
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
    public void doSave(Resume resume) {
        storageList.add(resume);
    }

    @Override
    public void doDelete(String uuid) {
        Iterator<Resume> iterator = storageList.iterator();
        while (iterator.hasNext()) {
            Resume resume = iterator.next();
            if (resume.getUuid().equals(uuid)) {
                iterator.remove();
            }
        }
    }

    @Override
    public Resume[] getAll() {
        return storageList.toArray(new Resume[0]);
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
