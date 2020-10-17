package com.javaops.webapp.storage;

import com.javaops.webapp.model.Resume;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    public Comparator<Resume> ArraySort = new Comparator<Resume>() {
        @Override
        public int compare(Resume o1, Resume o2) {
            return o1.getUuid().compareTo(o2.getUuid());
        }
    };

    public Resume[] sortArray() {
        Resume[] sortedStorage = getAll();
        Collections.sort(Arrays.asList(sortedStorage), ArraySort);
        return sortedStorage;
    }

    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(sortArray(), 0, size, searchKey);
    }
}
