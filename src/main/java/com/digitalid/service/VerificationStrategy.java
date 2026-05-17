package com.digitalid.service;

import com.digitalid.model.DigitalId;

public interface VerificationStrategy {
    boolean verify(DigitalId digitalId);
    String getOrganisationType();
}
