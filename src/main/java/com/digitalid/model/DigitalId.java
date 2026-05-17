package com.digitalid.model;

public class DigitalId {

    // Immutable fields
    private final String id;
    private final String dateOfBirth;
    private final String nationality;

    // Mutable fields
    private String name;
    private String address;

    // Status
    private IdStatus status;

    // Constructor
    public DigitalId(String id, String name, String dateOfBirth, String nationality, String address) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.address = address;
        this.status = IdStatus.ACTIVE;

    }
}
