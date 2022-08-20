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
    
    postgres=# create database parking__lot;
    
    CREATE DATABASE
    
    postgres=# grant all privileges on database parking__lot to sanket;
    
    GRANT

---

### Entities
1. ParkingLot
2. Floor
3. Spot
    1. SpotType << enum >>
    2. SpotStatus << enum >>
    3. ElectricSpot
4. Charger
5. Gate
    1. GateStatus << enum >>
    2. GateType << enum >>
6. EntryGate
7. ExitGate
8. DisplayBoard
9. Operator
10. Ticket
11. Bill
    1. BilPaymentStatus << enum >>
12. Payment
    1. PaymentMode << enum >>
    2. PaymentStatus << enum >>
13. Vehicle
    1. VehicleType << enum >>

---

### Strategies

1. FeesCalculationStrategy
2. PaymentStrategy - [third party]
3. SpotAssignmentStrategy

---

### Relationships

1. ParkingLot <-> Floor  - (1 : M) mappedBy=parkingLot
2. ParkingLot <-> Gate - (1 : M) mappedBy=parkingLot
3. Floor <-> Spot - (1 : M) mappedBy=floor
4. Floor <-> ParkingLot - (M : 1)
5. Spot <-> Floor - (M : 1)
6. Gate <-> Operator - (1 : 1)
7. Gate <-> ParkingLot - (M : 1)
8. EntryGate <-> DisplayBoard (1 : 1)
9. DisplayBoard <-> EntryGate (1: 1) mappedBy=displayBoard
10. Operator <-> Gate (1 : 1) mappedBy=operator
11. Ticket <-> Vehicle (M : 1)
12. Ticket <-> Spot (1 : 1)
13. Ticket <-> EntryGate (M : 1)
14. Ticket <-> Operator (M : 1)
15. Ticket <-> Floor (M : 1)
16. Ticket <-> ParkingLot (M : 1)
17. Bill <-> ExitGate (M : 1)
18. Bill <-> Ticket (1 : 1)
19. Bill <-> Operator (M : 1)
20. Payment <-> Bill (M : 1)
21. Charger <-> ElectricSpot (1 : 1)
22. ElectricSpot <-> Charger (1 : 1) mappedBy=electricSpot

---

### Class Diagram

![image](https://github.com/sanketwakhare/lld-designs/blob/master/design-parking-lot/src/diagrams/class-diagram-parking__lot.png)


---

### Schema Diagram

![image](https://github.com/sanketwakhare/lld-designs/blob/master/design-parking-lot/src/diagrams/schema-diagram-parking__lot.png)
