package com.javaops.webapp.storage;

import com.javaops.webapp.storage.serializer.ObjectStreamSerializer;

public class ObjectStreamFileStorageTest extends AbstractStorageTest {

    public ObjectStreamFileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamSerializer()));
    }
}