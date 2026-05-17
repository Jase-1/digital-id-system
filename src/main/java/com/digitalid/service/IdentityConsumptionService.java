package com.digitalid.service;

import com.digitalid.audit.AuditLog;
import com.digitalid.model.DigitalId;
import com.digitalid.repository.DigitalIdRepository;

public class IdentityConsumptionService {

    private final DigitalIdRepository repository;
    private final AuditLog auditLog;

    public IdentityConsumptionService() {
        this.repository = DigitalIdRepository.getInstance();
        this.auditLog = AuditLog.getInstance();
    }

    public boolean verifyIdentity(String id, VerificationStrategy strategy) {
        DigitalId digitalId = repository.findById(id);
        if (digitalId == null) {
            throw new IllegalArgumentException("No Digital ID found with ID: " + id);
        }
        return strategy.verify(digitalId);
    }

    public String getVerificationResult(String id, VerificationStrategy strategy) {
        boolean result = verifyIdentity(id, strategy);
        String message = strategy.getOrganisationType() + " verification for ID " + id + ": " + (result ? "APPROVED" : "REJECTED");
        auditLog.onEvent(message);
        return message;
    }
}