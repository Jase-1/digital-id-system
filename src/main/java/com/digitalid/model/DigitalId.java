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

    public String getId() { return id; }
    public String getName() { return name; }
    public String getDateOfBirth() { return dateOfBirth; }
    public String getNationality() { return nationality; }
    public String getAddress() { return address; }
    public IdStatus getStatus() { return status; }

    public void setName(String name) {
        if (this.status == IdStatus.REVOKED) {
            throw new IllegalStateException("Cannot update a revoked Digital ID");
        }
        this.name = name;
    }

    public void setAddress(String address) {
        if (this.status == IdStatus.REVOKED) {
            throw new IllegalStateException("Cannot update a revoked Digital ID");
        }
        this.address = address;
    }

    public void setStatus(IdStatus status) {
        this.status = status;
    }




}
