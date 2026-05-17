package com.digitalid.service;

import com.digitalid.model.DigitalId;
import com.digitalid.model.IdStatus;

public class TaxAuthorityVerification implements VerificationStrategy {

    @Override
    public boolean verify(DigitalId digitalId) {
        return digitalId.getStatus() == IdStatus.ACTIVE;
    }

    @Override
    public String getOrganisationType() {
        return "Tax Authority";
    }
}