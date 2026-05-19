# digital-id-system

Github Repository link:
https://github.com/Jase-1/digital-id-system

## Prerequisites:

Java 14 or above
Maven installed

## Steps to run:

1. Clone the repository
- git clone https://github.com/Jase-1/digital-id-system
2. Navigate into the project folder
- cd digital-id-system 
3. Build the project
- mvn clean install
4. Run the system
- mvn exec:java -Dexec.mainClass="com.digitalid.Main"
5. To run the tests:
- mvn test

## What the system does

This system is a console-based backend platform for managing and consuming Digital IDs across a federated ecosystem of organisations. The platform is divided into two distinct capabilities as required by the system design: identity management and identity consumption.


Identity management is the exclusive responsibility of a central authority, which is the only entity permitted to create new Digital IDs, update permitted attributes such as name and address, and manage identity status. Certain attributes, such as date of birth and nationality, are immutable once set, while others may be modified only when the identity is in a valid state - for example, a revoked Digital ID cannot be updated.


Identity consumption is handled separately and allows authorised organisations to interact with the platform for verification purposes. Different organisations have different verification requirements - a tax authority verifies that an identity is active, a driving licence authority additionally checks eligibility conditions, and an employer performs a simple active status check. These operations are specific to the purpose for which an organisation requires the data provided by a Digital ID. Additionally, organisations do not modify identity data and do not communicate with each other directly.


All requests are validated before any operation is performed. Invalid requests - such as attempting to create a duplicate ID or update a revoked identity - are rejected consistently with a clear error response. Key actions including identity creation, status changes, updates, and verification requests, are recorded by the audit log, allowing system behaviour to be examined.

## Packages structure and contents

The system is organised into four packages, each with a distinct responsibility. 
## Model 
Contains DigitalId.java, which represents a single digital identity with immutable fields (id, date of birth, nationality) enforced using Java's final keyword, and mutable fields (name, address). Also contains IdStatus, an enum defining the three possible states — ACTIVE, SUSPENDED, REVOKED.
## Repository 
This contains a single repository file that provides a data storage for Digital IDs to be stored in a centralised location as a Hashmap. It also provides functionality for Digital IDs to be accessed by saving and locating ids through the data storage.
## Service
This package consists of the management and consumption services plus the three verification strategies which are standardised through a verification strategy interface. The files within this package are what differentiates the level of access each organisation has to Digital IDs, the interaction between organisations and the data repository and the role of the central authority in managing Digital IDs.
## Audit
This package contains the auditlog which logs key operations and events that take place related to Digital IDs


## Design Patterns and Code quality decisions featured
Singleton - The Singleton pattern was applied to both the DigitalIdRepository and AuditLog classes. This ensures that only one instance of each exists throughout the lifetime of the system, meaning all components share the same data store and the same audit record. This prevents data inconsistency that would arise if multiple instances were created independently.


Strategy - The Strategy pattern was applied to the identity verification logic. Each organisation type implements a common VerificationStrategy interface but defines its own verification behaviour. This means the IdentityConsumptionService does not need to know which organisation it is dealing with - it simply calls verify() and the correct behaviour executes. This avoids a large if/else chain and means new organisation types can be added without modifying existing service code.


Observer - The Observer pattern was applied to the audit logging system. Rather than embedding logging logic directly inside the service classes, the AuditLog acts as an observer that is notified when key events occur. This keeps the logging concern separate from the business logic, meaning neither service needs to know how events are recorded — only that they should be reported.


## Code Quality Decisions 
Throughout development, an awareness of code smells from the module was maintained to ensure the codebase remained clean, readable, and maintainable.


## Primitive Obsession 
This was avoided by representing identity status as an IdStatus enum rather than a String. This was a deliberate decision because only three states are valid — ACTIVE, SUSPENDED, and REVOKED. Using a String would allow invalid values to be assigned without the compiler catching them, weakening the domain model. Using an enum means only valid states are ever possible.


## Long Class  
This was avoided by ensuring each class has a single, clearly defined responsibility. For example, DigitalId represents what an identity is, DigitalIdRepository handles storage, IdentityManagementService handles central authority operations, and AuditLog handles event recording. This approach ensures that a class with too many unrelated functions should be split into smaller, focused components.




