package com.javaops.webapp.storage;

import com.javaops.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void update(Resume r) {
        if (!contains(r.getUuid())) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(r.getUuid())) {
                    storage[i] = r;
                }
            }
        } else {
            System.out.println("Resume " + r.getUuid() + " doesn't exist");
        }
    }

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    public void save(Resume r) {
        if (contains(r.getUuid())) {
            System.out.println("Resume " + r.getUuid() + " already exists");
            return;

//        for (int i = 0; i < size; i++) {
//            if (storage[i].getUuid().equals(r.getUuid())) {
//                System.out.println("Resume " + r.getUuid() + " already exists");
//                return;
//            }
//        }
        } else if (size < storage.length) {
            storage[size] = r;
            size++;
        } else {
            System.out.println("Storage is full");
        }
    }

    public Resume get(String uuid) {
        if (contains(uuid)) {
            for (int i = 0; i < size; i++) {
                if (uuid.equals(storage[i].getUuid())) {
                    return storage[i];
                }
            }
        }
        return null;
    }

    public boolean contains(String uuid) {
        boolean present = false;

        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                present = true;
                break;
            }
        }
        return present;
    }

    public void delete(String uuid) {
        if (contains(uuid)) {
            for (int i = 0; i < size; i++) {
                if (uuid.equals(storage[i].getUuid())) {
                    storage[i] = storage[size - 1];
                    storage[size - 1] = null;
                    size--;
                }
            }
        } else {
            System.out.println("Resume " + uuid + " doesn't exist");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] allResumes = new Resume[size];
        if (size >= 0) System.arraycopy(storage, 0, allResumes, 0, size);
//        for (int i = 0; i < size; i++) {
//            allResumes[i] = storage[i];
//        }
        return allResumes;
    }

    public int size() {
        return size;
    }
}
