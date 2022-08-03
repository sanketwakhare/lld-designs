# Design Parking Lot

### Requirements Gathering
1. A Parking Lot has multiple floors
2. Each Floor has multiple Spots
3. Spots are of different types like 
   1. Car
   2. Bike
   3. Heavy Vehicle
   4. Electric
   5. Premium Vehicle
4. A parking lot has multiple Entry gates and multiple Exit gates
5. At Entry gate, a Ticket is generated
6. A Vehicle has to part at the Spot mentioned on the Ticket
7. When a Vehicle reaches Exit gate, a Bill is generated
8. Fess is calculated based on below parameters
   1. Type of Slot
   2. Duration of Parking
   3. Entry Time
9. In future there can be other ways to calculate fees as well
10. A Bill can be paid via either
    1. Cash
    2. Credit Card
    3. Debit Card
    4. UPI
11. Entry gate has DisplayBoard to show the available spots for each spot type
12. For every type of spot, there is different per hour cost
13. For different time ranges, there are different multipliers for fees calculations
14. Every parking lot has an address
15. Every Floor can have different types of spot
16. For Electric Vehicles, the fees also include the amount of charging/electricity consumed.

---

### Database Setup
    postgres=# create user sanket with password 'sanket';
    
    CREATE ROLE
    
    `postgres=# create database parking__lot;
    `
    
    CREATE DATABASE
    
    `postgres=# grant all privileges on database parking__lot to sanket;`
    
    GRANT

---

### Entities
1. ParkingLot
2. Floor
3. Spot
   1. SpotType << enum >>
   2. SpotStatus << enum >>
4. Gate
   1. GateStatus << enum >>
   2. GateType << enum >>
5. EntryGate
6. ExitGate
7. DisplayBoard
8. Operator
9. Ticket
10. Bill
    1. BilPaymentStatus << enum >>
11. Payment
    1. PaymentMode << enum >>
    2. PaymentStatus << enum >>
12. Vehicle
    1. VehicleType << enum >>
13. ElectricVehicle
14. Charger

### Strategies
1. FeesCalculationStrategy
2. PaymentStrategy - [third party]
3. SpotAssignmentStrategy

---

### Relationships

1. ParkingLot <-> Floor  - (1 : M)
2. ParkingLot <-> EntryGate - (1 : M)
3. ParkingLot <-> ExitGate - (1 : M)
4. Floor <-> Spot - (1 : M)
5. Spot <-> Floor - (M : 1) mappedBy
6. EntryGate <-> DisplayBoard (1 : 1)
7. DisplayBoard <-> ParkingLot (M: 1)
8. Ticket <-> Vehicle (M : 1)
9. Ticket <-> Spot (1 : 1)
10. Ticket <-> EntryGate (M : 1)
11. Ticket <-> Operator (M : 1)
12. Ticket <-> Floor (M : 1)
13. Ticket <-> ParkingLot (M : 1)
14. Bill <-> ExitGate (M : 1)
15. Bill <-> Ticket (1 : 1)
16. Bill <-> Operator (M : 1)
17. Payment <-> Bill (M : 1)
18. ElectricVehicle <-> Charger (1 : 1)

---

### Class Diagram

    TODO
---

### Schema Diagram

    TODO