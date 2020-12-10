package com.javaops.webapp.storage;

import com.javaops.webapp.storage.serializer.ObjectStreamSerializer;

public class ObjectStreamPathStorageTest extends AbstractStorageTest {

    public ObjectStreamPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.toString(), new ObjectStreamSerializer()));
    }
}