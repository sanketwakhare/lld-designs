package com.sanket.designparkinglot;

import com.sanket.designparkinglot.models.bill.Bill;
import com.sanket.designparkinglot.models.displayboard.DisplayBoard;
import com.sanket.designparkinglot.models.floor.Floor;
import com.sanket.designparkinglot.models.gates.EntryGate;
import com.sanket.designparkinglot.models.gates.ExitGate;
import com.sanket.designparkinglot.models.operator.Operator;
import com.sanket.designparkinglot.models.parkinglot.ParkingLot;
import com.sanket.designparkinglot.models.payment.Payment;
import com.sanket.designparkinglot.models.spot.Spot;
import com.sanket.designparkinglot.models.spot.SpotType;
import com.sanket.designparkinglot.models.ticket.Ticket;
import com.sanket.designparkinglot.models.vehicle.Vehicle;
import com.sanket.designparkinglot.models.vehicle.VehicleType;
import com.sanket.designparkinglot.strategies.feescalculator.FeesCalculatorStrategy;
import com.sanket.designparkinglot.strategies.feescalculator.NormalFeesCalculatorStrategy;
import com.sanket.designparkinglot.strategies.paymentstrategy.PaymentStrategy;
import com.sanket.designparkinglot.strategies.paymentstrategy.UPIPaymentStrategy;
import com.sanket.designparkinglot.strategies.spotassignment.RandomSpotAssignmentStrategy;
import com.sanket.designparkinglot.strategies.spotassignment.SpotAssignmentStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@SpringBootTest
class DesignParkingLotApplicationTests {

    @Test
    void contextLoads() {

        ParkingLot parkingLot = new ParkingLot();

        // Create Floors
        Floor floor1 = new Floor("G1");
        Floor floor2 = new Floor("G2");
        List<Floor> floors = new ArrayList<>();
        floors.add(floor1);
        floors.add(floor2);
        parkingLot.setFloors(floors);

        // add spots for each floor
        addFloorSpots(floor1);
        addFloorSpots(floor2);

        // display board
        DisplayBoard displayBoard = new DisplayBoard(parkingLot);

        // Spot assignment strategy
        SpotAssignmentStrategy spotAssignmentStrategy = new RandomSpotAssignmentStrategy();

        // Fees calculation Strategy
        FeesCalculatorStrategy feesCalculatorStrategy = new NormalFeesCalculatorStrategy();

        // Operators
        Operator operator1 = new Operator("person1");
        Operator operator2 = new Operator("person2");
        Operator operator3 = new Operator("person3");
        Operator operator4 = new Operator("person4");

        // add entry gate and exit gates
        EntryGate entryGate1 = new EntryGate(displayBoard, "EntryGate1");
        entryGate1.setOperator(operator1);
        EntryGate entryGate2 = new EntryGate(displayBoard, "EntryGate2");
        entryGate2.setOperator(operator2);
        ExitGate exitGate1 = new ExitGate("ExitGate1");
        exitGate1.setOperator(operator3);
        ExitGate exitGate2 = new ExitGate("ExitGate2");
        exitGate2.setOperator(operator4);


        Vehicle vehicle1 = new Vehicle("MH12PR1234", VehicleType.BIKE);
        Ticket ticket = entryGate2.generateTicket(parkingLot, vehicle1, spotAssignmentStrategy);
        Assert.notNull(ticket, "ticket could not be generated");
        System.out.println("ticket generated");

        entryGate1.displayBoard();

        // pay bill
        Bill bill = exitGate1.generateBill(ticket, feesCalculatorStrategy);

        PaymentStrategy paymentStrategy = new UPIPaymentStrategy();
        Payment payment = paymentStrategy.payBill(bill);
        System.out.println("payment refId: " + payment.getRefId() + " payment status: " + payment.getPaymentStatus());

    }

    private void addFloorSpots(Floor floor) {
        // Create Spots floor
        List<Spot> spots = new ArrayList<>();
        // 20 spots for BIKE
        int count = 1;
        for (int i = 1; i <= 20; i++) {
            spots.add(new Spot(floor, SpotType.BIKE, floor.getFloorNumber() + "-" + count++));
        }
        // 10 spots for CAR
        for (int i = 1; i <= 10; i++) {
            spots.add(new Spot(floor, SpotType.CAR, floor.getFloorNumber() + "-" + count++));
        }
        // 5 spots for ELECTRIC CAR
        for (int i = 1; i <= 5; i++) {
            spots.add(new Spot(floor, SpotType.ELECTRIC, floor.getFloorNumber() + "-" + count++));
        }
        // 5 spots for HEAVY
        for (int i = 1; i <= 5; i++) {
            spots.add(new Spot(floor, SpotType.HEAVY, floor.getFloorNumber() + "-" + count++));
        }
        // 5 spots for PREMIUM
        for (int i = 1; i <= 5; i++) {
            spots.add(new Spot(floor, SpotType.PREMIUM, floor.getFloorNumber() + "-" + count++));
        }
        floor.setSpots(spots);
    }

}
