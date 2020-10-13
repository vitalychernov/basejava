package com.javaops.webapp.storage;

import com.javaops.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int size = 0;

    public void update(Resume resume) {
        if (getIndex(resume.getUuid()) == -1) {
            System.out.println("Resume " + resume.getUuid() + " doesn't exist");
        } else storage[getIndex(resume.getUuid())] = resume;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (getIndex(resume.getUuid()) == 1) {
            System.out.println("Resume " + resume.getUuid() + " already exists");
        } else if (size < storage.length) {
            storage[size] = resume;
            size++;
        } else {
            System.out.println("Storage is full");
        }
    }

    public Resume get(String uuid) {
        if (getIndex(uuid) == 1) {
            return storage[getIndex(uuid)];
        } else System.out.println("Resume " + uuid + " doesn't exist");
        return null;
    }

    public void delete(String uuid) {
        if (getIndex(uuid) == 1) {
            storage[getIndex(uuid)] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else System.out.println("Resume " + uuid + " doesn't exist");
    }

    int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return 1;
            }
        }
        return -1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] allResumes = new Resume[size];
        allResumes = Arrays.copyOf(storage, size);
        return allResumes;
    }

    public int size() {
        return size;
    }
}
