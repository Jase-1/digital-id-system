package com.digitalid.repository;

import com.digitalid.model.DigitalId;
import java.util.HashMap;
import java.util.Map;

public class DigitalIdRepository {

    private static DigitalIdRepository instance;
    private final Map<String, DigitalId> store;

    private DigitalIdRepository() {
        store = new HashMap<>();
    }

    public static DigitalIdRepository getInstance() {
        if (instance == null) {
            instance = new DigitalIdRepository();
        }
        return instance;
    }

    public void save(DigitalId digitalId) {
        store.put(digitalId.getId(), digitalId);
    }

    public DigitalId findById(String id) {
        return store.get(id);
    }

    public boolean exists(String id) {
        return store.containsKey(id);
    }
}