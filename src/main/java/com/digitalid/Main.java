package com.digitalid;

import com.digitalid.audit.AuditLog;
import com.digitalid.service.*;
import com.digitalid.model.IdStatus;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        IdentityManagementService managementService = new IdentityManagementService();
        IdentityConsumptionService consumptionService = new IdentityConsumptionService();
        AuditLog auditLog = AuditLog.getInstance();
        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running) {
            System.out.println("\n=== Digital ID System ===");
            System.out.println("1. Create Digital ID");
            System.out.println("2. Update Name");
            System.out.println("3. Update Address");
            System.out.println("4. Change Status");
            System.out.println("5. Verify Identity (Tax Authority)");
            System.out.println("6. Verify Identity (Driving Licence)");
            System.out.println("7. Verify Identity (Employer)");
            System.out.println("8. View Audit Log");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        System.out.print("Enter ID: ");
                        String id = scanner.nextLine();
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Date of Birth: ");
                        String dob = scanner.nextLine();
                        System.out.print("Enter Nationality: ");
                        String nationality = scanner.nextLine();
                        System.out.print("Enter Address: ");
                        String address = scanner.nextLine();
                        managementService.createIdentity(id, name, dob, nationality, address);
                        System.out.println("Digital ID created successfully.");
                        break;

                    case "2":
                        System.out.print("Enter ID: ");
                        String updateNameId = scanner.nextLine();
                        System.out.print("Enter new Name: ");
                        String newName = scanner.nextLine();
                        managementService.updateName(updateNameId, newName);
                        System.out.println("Name updated successfully.");
                        break;

                    case "3":
                        System.out.print("Enter ID: ");
                        String updateAddressId = scanner.nextLine();
                        System.out.print("Enter new Address: ");
                        String newAddress = scanner.nextLine();
                        managementService.updateAddress(updateAddressId, newAddress);
                        System.out.println("Address updated successfully.");
                        break;

                    case "4":
                        System.out.print("Enter ID: ");
                        String statusId = scanner.nextLine();
                        System.out.println("Select new status: 1. ACTIVE  2. SUSPENDED  3. REVOKED");
                        String statusChoice = scanner.nextLine();
                        IdStatus newStatus = switch (statusChoice) {
                            case "1" -> IdStatus.ACTIVE;
                            case "2" -> IdStatus.SUSPENDED;
                            case "3" -> IdStatus.REVOKED;
                            default -> throw new IllegalArgumentException("Invalid status choice");
                        };
                        managementService.changeStatus(statusId, newStatus);
                        System.out.println("Status changed successfully.");
                        break;

                    case "5":
                        System.out.print("Enter ID: ");
                        String taxId = scanner.nextLine();
                        System.out.println(consumptionService.getVerificationResult(taxId, new TaxAuthorityVerification()));
                        break;

                    case "6":
                        System.out.print("Enter ID: ");
                        String drivingId = scanner.nextLine();
                        System.out.println(consumptionService.getVerificationResult(drivingId, new DrivingLicenceVerification()));
                        break;

                    case "7":
                        System.out.print("Enter ID: ");
                        String employerId = scanner.nextLine();
                        System.out.println(consumptionService.getVerificationResult(employerId, new EmployerVerification()));
                        break;

                    case "8":
                        auditLog.printAllLogs();
                        break;

                    case "9":
                        running = false;
                        System.out.println("Exiting system.");
                        break;

                    default:
                        System.out.println("Invalid option, please try again.");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}