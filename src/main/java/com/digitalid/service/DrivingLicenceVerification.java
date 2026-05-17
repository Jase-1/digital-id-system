package com.digitalid.service;

import com.digitalid.model.DigitalId;
import com.digitalid.model.IdStatus;

public class DrivingLicenceVerification implements VerificationStrategy {

    @Override
    public boolean verify(DigitalId digitalId) {
        return digitalId.getStatus() == IdStatus.ACTIVE
                && digitalId.getNationality() != null
                && !digitalId.getNationality().isEmpty();
    }

    @Override
    public String getOrganisationType() {
        return "Driving Licence Authority";
    }
}