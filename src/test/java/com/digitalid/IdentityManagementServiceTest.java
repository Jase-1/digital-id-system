package com.digitalid;

import com.digitalid.model.IdStatus;
import com.digitalid.service.IdentityManagementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IdentityManagementServiceTest {

    private IdentityManagementService managementService;

    @BeforeEach
    void setUp() {
        managementService = new IdentityManagementService();
    }

    @Test
    void testCreateIdentitySuccessfully() {
        assertDoesNotThrow(() ->
                managementService.createIdentity("001", "John Smith", "01/01/1990", "British", "London")
        );
    }

    @Test
    void testCreateDuplicateIdentityThrowsException() {
        managementService.createIdentity("002", "Jane Doe", "02/02/1992", "British", "Manchester");
        assertThrows(IllegalArgumentException.class, () ->
                managementService.createIdentity("002", "Jane Doe", "02/02/1992", "British", "Manchester")
        );
    }

    @Test
    void testUpdateNameSuccessfully() {
        managementService.createIdentity("003", "Bob Jones", "03/03/1985", "British", "Birmingham");
        assertDoesNotThrow(() ->
                managementService.updateName("003", "Robert Jones")
        );
    }

    @Test
    void testUpdateRevokedIdentityThrowsException() {
        managementService.createIdentity("004", "Alice Brown", "04/04/1988", "British", "Leeds");
        managementService.changeStatus("004", IdStatus.REVOKED);
        assertThrows(IllegalStateException.class, () ->
                managementService.updateName("004", "Alice Smith")
        );
    }

    @Test
    void testChangeStatusSuccessfully() {
        managementService.createIdentity("005", "Charlie Wilson", "05/05/1995", "British", "Bristol");
        managementService.changeStatus("005", IdStatus.SUSPENDED);
        assertEquals(IdStatus.SUSPENDED, managementService.findById("005").getStatus());
    }
}
