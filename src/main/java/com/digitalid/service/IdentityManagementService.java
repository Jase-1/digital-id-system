package com.digitalid.service;

import com.digitalid.model.DigitalId;
import com.digitalid.model.IdStatus;
import com.digitalid.repository.DigitalIdRepository;

public class IdentityManagementService {

    private final DigitalIdRepository repository;

    public IdentityManagementService() {
        this.repository = DigitalIdRepository.getInstance();
    }

    public DigitalId createIdentity(String id, String name, String dateOfBirth, String nationality, String address) {
        if (repository.exists(id)) {
            throw new IllegalArgumentException("A Digital ID with this ID already exists: " + id);
        }
        DigitalId digitalId = new DigitalId(id, name, dateOfBirth, nationality, address);
        repository.save(digitalId);
        return digitalId;
    }

    public void updateName(String id, String newName) {
        DigitalId digitalId = getOrThrow(id);
        digitalId.setName(newName);
    }

    public void updateAddress(String id, String newAddress) {
        DigitalId digitalId = getOrThrow(id);
        digitalId.setAddress(newAddress);
    }

    public void changeStatus(String id, IdStatus newStatus) {
        DigitalId digitalId = getOrThrow(id);
        digitalId.setStatus(newStatus);
    }

    private DigitalId getOrThrow(String id) {
        DigitalId digitalId = repository.findById(id);
        if (digitalId == null) {
            throw new IllegalArgumentException("No Digital ID found with ID: " + id);
        }
        return digitalId;
    }
}