package com.javaops.webapp.storage;

import com.javaops.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insertElement(Resume r, int index) {
        int indexForInsert = - index - 1;
        System.arraycopy(storage, indexForInsert, storage, indexForInsert + 1, size - indexForInsert);
        storage[indexForInsert] = r;
    }

    @Override
    protected void fillDeletedElement(int index) {
        if (index < size-1){
            System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        }
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "Какое-то имя");
        return Arrays.binarySearch(storage, 0, size, searchKey, Comparator.comparing(Resume::getUuid));
    }
}