package com.digitalid;

import com.digitalid.model.DigitalId;
import com.digitalid.model.IdStatus;
import com.digitalid.service.TaxAuthorityVerification;
import com.digitalid.service.DrivingLicenceVerification;
import com.digitalid.service.EmployerVerification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VerificationStrategyTest {

    private DigitalId activeId;
    private DigitalId suspendedId;
    private DigitalId revokedId;

    @BeforeEach
    void setUp() {
        activeId = new DigitalId("001", "John Smith", "01/01/1990", "British", "London");
        suspendedId = new DigitalId("002", "Jane Doe", "02/02/1992", "British", "Manchester");
        suspendedId.setStatus(IdStatus.SUSPENDED);
        revokedId = new DigitalId("003", "Bob Jones", "03/03/1985", "British", "Birmingham");
        revokedId.setStatus(IdStatus.REVOKED);
    }

    // Tax Authority Tests
    @Test
    void testTaxAuthorityApprovesActiveId() {
        TaxAuthorityVerification strategy = new TaxAuthorityVerification();
        assertTrue(strategy.verify(activeId));
    }

    @Test
    void testTaxAuthorityRejectsSuspendedId() {
        TaxAuthorityVerification strategy = new TaxAuthorityVerification();
        assertFalse(strategy.verify(suspendedId));
    }

    @Test
    void testTaxAuthorityRejectsRevokedId() {
        TaxAuthorityVerification strategy = new TaxAuthorityVerification();
        assertFalse(strategy.verify(revokedId));
    }

    // Driving Licence Tests
    @Test
    void testDrivingLicenceApprovesActiveIdWithNationality() {
        DrivingLicenceVerification strategy = new DrivingLicenceVerification();
        assertTrue(strategy.verify(activeId));
    }

    @Test
    void testDrivingLicenceRejectsSuspendedId() {
        DrivingLicenceVerification strategy = new DrivingLicenceVerification();
        assertFalse(strategy.verify(suspendedId));
    }

    // Employer Tests
    @Test
    void testEmployerApprovesActiveId() {
        EmployerVerification strategy = new EmployerVerification();
        assertTrue(strategy.verify(activeId));
    }

    @Test
    void testEmployerRejectsRevokedId() {
        EmployerVerification strategy = new EmployerVerification();
        assertFalse(strategy.verify(revokedId));
    }
}