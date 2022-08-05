package com.sanket.designparkinglot;

import com.sanket.designparkinglot.controllers.*;
import com.sanket.designparkinglot.dtos.base.response.ResponseStatus;
import com.sanket.designparkinglot.dtos.displayboard.CreateDisplayBoardRequestDto;
import com.sanket.designparkinglot.dtos.displayboard.CreateDisplayBoardResponseDto;
import com.sanket.designparkinglot.dtos.floor.*;
import com.sanket.designparkinglot.dtos.gate.*;
import com.sanket.designparkinglot.dtos.operator.CreateOperatorRequestDto;
import com.sanket.designparkinglot.dtos.operator.CreateOperatorResponseDto;
import com.sanket.designparkinglot.dtos.parkinglot.*;
import com.sanket.designparkinglot.dtos.spot.CreateSpotRequestDto;
import com.sanket.designparkinglot.dtos.spot.CreateSpotResponseDto;
import com.sanket.designparkinglot.models.bill.Bill;
import com.sanket.designparkinglot.models.displayboard.DisplayBoard;
import com.sanket.designparkinglot.models.floor.Floor;
import com.sanket.designparkinglot.models.gates.EntryGate;
import com.sanket.designparkinglot.models.gates.ExitGate;
import com.sanket.designparkinglot.models.gates.GateStatus;
import com.sanket.designparkinglot.models.gates.GateType;
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
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DesignParkingLotApplicationTests {

    @Autowired
    private ParkingLotController parkingLotController;

    @Autowired
    private FloorController floorController;

    @Autowired
    private SpotController spotController;

    @Autowired
    private DisplayBoardController displayBoardController;

    @Autowired
    private GateController gateController;

    @Autowired
    private OperatorController operatorController;

    @Test
    @Disabled
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
        DisplayBoard displayBoard = new DisplayBoard();

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

        entryGate1.displayBoard(parkingLot);

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


    @Test
    @Order(1)
    void testCreateParkingLot() {
        CreateParkingLotRequestDto createParkingLotRequestDto = new CreateParkingLotRequestDto();
        createParkingLotRequestDto.setAddress("Central Mall, Pune");
        CreateParkingLotResponseDto createParkingLotResponseDto = parkingLotController.addParkingLot(createParkingLotRequestDto);
        Assert.notNull(createParkingLotResponseDto, "something went wrong");
        Assert.isTrue(ResponseStatus.SUCCESS.equals(createParkingLotResponseDto.getResponseStatus()), createParkingLotResponseDto.getMessage());
        System.out.println("parking lot created");
    }

    @Test
    @Order(2)
    void testCreateParkingLotWithFloors() {
        CreateParkingLotRequestDto createParkingLotRequestDto = new CreateParkingLotRequestDto();
        createParkingLotRequestDto.setAddress("City Mall, Mumbai");
        createParkingLotRequestDto.setNumberOfFloors(3);
        CreateParkingLotResponseDto createParkingLotResponseDto = parkingLotController.addParkingLot(createParkingLotRequestDto);
        Assert.notNull(createParkingLotResponseDto, "something went wrong");
        Assert.isTrue(ResponseStatus.SUCCESS.equals(createParkingLotResponseDto.getResponseStatus()), createParkingLotResponseDto.getMessage());
        System.out.println("parking lot created");
    }

    @Test
    @Order(3)
    void testCreateAndAssignFloorToParkingLot() {
        CreateFloorRequestDto createFloorRequestDto = new CreateFloorRequestDto();
        createFloorRequestDto.setParkingLotId(1L);
        createFloorRequestDto.setFloorNumber("G1");
        CreateFloorResponseDto createFloorResponseDto = floorController.addFloor(createFloorRequestDto);
        Assert.notNull(createFloorResponseDto, "something went wrong");
        Assert.isTrue(ResponseStatus.SUCCESS.equals(createFloorResponseDto.getResponseStatus()), createFloorResponseDto.getMessage());
        System.out.println("floor created");
    }

    @Test
    @Order(4)
    void testDeleteParkingLotById() {
        //create new paring lot with 5 floors
        CreateParkingLotRequestDto createParkingLotRequestDto = new CreateParkingLotRequestDto();
        createParkingLotRequestDto.setAddress("Airport, Jodhpur");
        createParkingLotRequestDto.setNumberOfFloors(5);
        CreateParkingLotResponseDto createParkingLotResponseDto = parkingLotController.addParkingLot(createParkingLotRequestDto);
        Assert.notNull(createParkingLotResponseDto, "something went wrong");
        Assert.isTrue(ResponseStatus.SUCCESS.equals(createParkingLotResponseDto.getResponseStatus()), createParkingLotResponseDto.getMessage());
        System.out.println("parking lot created");

        // delete parking lot by id
        Long parkingLotId = createParkingLotResponseDto.getParkingLot().getId();
        DeleteParkingLotRequestDto deleteParkingLotRequestDto = new DeleteParkingLotRequestDto();
        deleteParkingLotRequestDto.setParkingLotId(parkingLotId);
        DeleteParkingLotResponseDto deleteParkingLotResponseDto = parkingLotController.deleteParkingLotById(deleteParkingLotRequestDto);
        Assert.notNull(deleteParkingLotResponseDto, "something went wrong");
        Assert.isTrue(ResponseStatus.SUCCESS.equals(deleteParkingLotResponseDto.getResponseStatus()), deleteParkingLotResponseDto.getMessage());
        System.out.println("parking lot deleted successfully");
    }

    @Test
    @Order(5)
    void testAddSpot() {
        CreateSpotRequestDto createSpotRequestDto = new CreateSpotRequestDto();
        createSpotRequestDto.setSpotNumber("G1-1");
        createSpotRequestDto.setSpotType(SpotType.BIKE);
        CreateSpotResponseDto createSpotResponseDto = spotController.addSpot(createSpotRequestDto);
        Assert.notNull(createSpotResponseDto, "something went wrong");
        Assert.isTrue(ResponseStatus.SUCCESS.equals(createSpotResponseDto.getResponseStatus()), createSpotResponseDto.getMessage());
        System.out.println("spot created successfully");
    }

    @Test
    @Order(6)
    void testAllocateSpot() {
        AllocateSpotRequestDto allocateSpotRequestDto = new AllocateSpotRequestDto();
        allocateSpotRequestDto.setFloorId(4L);
        allocateSpotRequestDto.setSpotId(1L);
        AllocateSpotResponseDto allocateSpotResponseDto = floorController.allocateSpot(allocateSpotRequestDto);
        Assert.notNull(allocateSpotResponseDto, "something went wrong");
        Assert.isTrue(ResponseStatus.SUCCESS.equals(allocateSpotResponseDto.getResponseStatus()), allocateSpotResponseDto.getMessage());
        System.out.println("spot allocated successfully");
    }

    @Test
    @Order(7)
    void testDeallocateSpot() {
        DeAllocateSpotRequestDto deAllocateSpotRequestDto = new DeAllocateSpotRequestDto();
        deAllocateSpotRequestDto.setFloorId(4L);
        deAllocateSpotRequestDto.setSpotId(1L);
        DeAllocateSpotResponseDto deAllocateSpotResponseDto = floorController.deallocateSpot(deAllocateSpotRequestDto);
        Assert.notNull(deAllocateSpotResponseDto, "something went wrong");
        Assert.isTrue(ResponseStatus.SUCCESS.equals(deAllocateSpotResponseDto.getResponseStatus()), deAllocateSpotResponseDto.getMessage());
        System.out.println("spot deallocated successfully");
    }

    @Test
    @Order(8)
    void testCreateSpotsForTwoFloors() {

        // Floor 1 spots
        // 5 spots for BIKE
        createAndAllocateSpots(SpotType.BIKE, 5, 1L, 1, "F1");
        // 5 spots for CAR
        createAndAllocateSpots(SpotType.CAR, 5, 1L, 100, "F1");
        // 2 spots for ELECTRIC CAR
        createAndAllocateSpots(SpotType.ELECTRIC, 2, 1L, 200, "F1");
        // 5 spots for HEAVY
        createAndAllocateSpots(SpotType.HEAVY, 5, 1L, 300, "F1");
        // 5 spots for PREMIUM
        createAndAllocateSpots(SpotType.PREMIUM, 5, 1L, 400, "F1");

        // Floor 2 spots
        // 5 spots for BIKE
        createAndAllocateSpots(SpotType.BIKE, 5, 2L, 1, "F2");
        // 5 spots for CAR
        createAndAllocateSpots(SpotType.CAR, 5, 2L, 100, "F2");
        // 2 spots for ELECTRIC CAR
        createAndAllocateSpots(SpotType.ELECTRIC, 2, 2L, 200, "F2");
        // 5 spots for HEAVY
        createAndAllocateSpots(SpotType.HEAVY, 5, 2L, 300, "F2");
        // 5 spots for PREMIUM
        createAndAllocateSpots(SpotType.PREMIUM, 5, 2L, 400, "F2");
    }


    @Disabled
    public void createAndAllocateSpots(SpotType spotType, int numberOfSpots, Long floorId, int startCountId, String spotSeries) {
        for (int i = 1; i <= numberOfSpots; i++) {
            CreateSpotRequestDto createSpotRequestDto = new CreateSpotRequestDto();
            createSpotRequestDto.setSpotNumber(spotSeries + "-" + startCountId++);
            createSpotRequestDto.setSpotType(spotType);
            CreateSpotResponseDto createSpotResponseDto = spotController.addSpot(createSpotRequestDto);

            Assert.notNull(createSpotResponseDto, "something went wrong");
            Assert.isTrue(ResponseStatus.SUCCESS.equals(createSpotResponseDto.getResponseStatus()), createSpotResponseDto.getMessage());

            Spot spot = createSpotResponseDto.getSpot();
            Long spotId = spot.getId();

            AllocateSpotRequestDto allocateSpotRequestDto = new AllocateSpotRequestDto();
            allocateSpotRequestDto.setSpotId(spotId);
            allocateSpotRequestDto.setFloorId(floorId);
            AllocateSpotResponseDto allocateSpotResponseDto = floorController.allocateSpot(allocateSpotRequestDto);

            Assert.notNull(allocateSpotResponseDto, "something went wrong");
            Assert.isTrue(ResponseStatus.SUCCESS.equals(allocateSpotResponseDto.getResponseStatus()), allocateSpotResponseDto.getMessage());
        }
    }

    @Test
    @Order(9)
    void testCreateDisplayBoard() {
        CreateDisplayBoardRequestDto createDisplayBoardRequestDto = new CreateDisplayBoardRequestDto();
        createDisplayBoardRequestDto.setDisplayBoardNumber("D43412864182");
        CreateDisplayBoardResponseDto createDisplayBoardResponseDto = displayBoardController.addDisplayBoard(createDisplayBoardRequestDto);
        Assert.notNull(createDisplayBoardResponseDto, "something went wrong");
        Assert.isTrue(ResponseStatus.SUCCESS.equals(createDisplayBoardResponseDto.getResponseStatus()), createDisplayBoardResponseDto.getMessage());
        System.out.println("display board created successfully");
    }

    @Test
    @Order(10)
    void testCreateEntryGate() {
        CreateGateRequestDto createGateRequestDto = new CreateGateRequestDto();
        createGateRequestDto.setGateNumber("Gate-101");
        createGateRequestDto.setGateType(GateType.ENTRY);
        createGateRequestDto.setGateStatus(GateStatus.OPEN);
        CreateGateResponseDto createGateResponseDto = gateController.addGate(createGateRequestDto);
        Assert.notNull(createGateResponseDto, "something went wrong");
        Assert.isTrue(ResponseStatus.SUCCESS.equals(createGateResponseDto.getResponseStatus()), createGateResponseDto.getMessage());
        System.out.println("entry gate created successfully");
    }

    @Test
    @Order(11)
    void testCreateExitGate() {
        CreateGateRequestDto createGateRequestDto = new CreateGateRequestDto();
        createGateRequestDto.setGateNumber("Gate-201");
        createGateRequestDto.setGateType(GateType.EXIT);
        createGateRequestDto.setGateStatus(GateStatus.OPEN);
        CreateGateResponseDto createGateResponseDto = gateController.addGate(createGateRequestDto);
        Assert.notNull(createGateResponseDto, "something went wrong");
        Assert.isTrue(ResponseStatus.SUCCESS.equals(createGateResponseDto.getResponseStatus()), createGateResponseDto.getMessage());
        System.out.println("exit gate created successfully");
    }

    @Test
    @Order(12)
    void testModifyGateStatus() {
        ModifyGateStatusRequestDto modifyGateStatusRequestDto = new ModifyGateStatusRequestDto();
        modifyGateStatusRequestDto.setGateId(2L);
        modifyGateStatusRequestDto.setGateStatus(GateStatus.CLOSED);
        ModifyGateStatusResponseDto modifyGateStatusResponseDto = gateController.modifyGateStatus(modifyGateStatusRequestDto);
        Assert.notNull(modifyGateStatusResponseDto, "something went wrong");
        Assert.isTrue(ResponseStatus.SUCCESS.equals(modifyGateStatusResponseDto.getResponseStatus()), modifyGateStatusResponseDto.getMessage());
        System.out.println("gate status modified successfully");
    }

    @Test
    @Order(13)
    void testAssignDisplayBoardToEntryGate() {
        AssignDisplayBoardRequestDto assignDisplayBoardRequestDto = new AssignDisplayBoardRequestDto();
        assignDisplayBoardRequestDto.setGateId(1L);
        assignDisplayBoardRequestDto.setDisplayBoardId(1L);
        AssignDisplayBoardResponseDto assignDisplayBoardResponseDto = gateController.assignDisplayBoard(assignDisplayBoardRequestDto);
        Assert.notNull(assignDisplayBoardResponseDto, "something went wrong");
        Assert.isTrue(ResponseStatus.SUCCESS.equals(assignDisplayBoardResponseDto.getResponseStatus()), assignDisplayBoardResponseDto.getMessage());
        System.out.println("display board assigned to entry gate successfully");
    }

    @Test
    @Order(14)
    void testAssignDisplayBoardToExitGate() {
        AssignDisplayBoardRequestDto assignDisplayBoardRequestDto = new AssignDisplayBoardRequestDto();
        assignDisplayBoardRequestDto.setGateId(2L);
        assignDisplayBoardRequestDto.setDisplayBoardId(1L);
        AssignDisplayBoardResponseDto assignDisplayBoardResponseDto = gateController.assignDisplayBoard(assignDisplayBoardRequestDto);
        Assert.notNull(assignDisplayBoardResponseDto, "something went wrong");
        Assert.isTrue(ResponseStatus.FAILURE.equals(assignDisplayBoardResponseDto.getResponseStatus()), "display board cannot be assigned to exit gate");
        System.out.println("display board cannot be assigned to exit gate");
    }

    @Test
    @Order(15)
    void testAssignEntryGateToParkingLot() {
        AssignGateRequestDto assignGateRequestDto = new AssignGateRequestDto();
        assignGateRequestDto.setGateId(1L);
        assignGateRequestDto.setParkingLotId(2L);
        AssignGateResponseDto assignGateResponseDto = parkingLotController.assignGate(assignGateRequestDto);
        Assert.notNull(assignGateResponseDto, "something went wrong");
        Assert.isTrue(ResponseStatus.SUCCESS.equals(assignGateResponseDto.getResponseStatus()), assignGateResponseDto.getMessage());
        System.out.println("gate assigned to parking lot successfully");
    }

    @Test
    @Order(16)
    void testAssignExitGateToParkingLot() {
        AssignGateRequestDto assignGateRequestDto = new AssignGateRequestDto();
        assignGateRequestDto.setGateId(2L);
        assignGateRequestDto.setParkingLotId(2L);
        AssignGateResponseDto assignGateResponseDto = parkingLotController.assignGate(assignGateRequestDto);
        Assert.notNull(assignGateResponseDto, "something went wrong");
        Assert.isTrue(ResponseStatus.SUCCESS.equals(assignGateResponseDto.getResponseStatus()), assignGateResponseDto.getMessage());
        System.out.println("gate assigned to parking lot successfully");
    }

    @Test
    @Order(17)
    void testCreateOperator() {
        CreateOperatorRequestDto createOperatorRequestDto = new CreateOperatorRequestDto();
        createOperatorRequestDto.setName("operator-01");
        CreateOperatorResponseDto createOperatorResponseDto = operatorController.addOperator(createOperatorRequestDto);
        Assert.notNull(createOperatorResponseDto, "something went wrong");
        Assert.isTrue(ResponseStatus.SUCCESS.equals(createOperatorResponseDto.getResponseStatus()), createOperatorResponseDto.getMessage());
        System.out.println("operator created successfully");
    }

    @Test
    @Order(18)
    void testAssignOperatorToEntryGate() {
        AssignOperatorRequestDto assignOperatorRequestDto = new AssignOperatorRequestDto();
        assignOperatorRequestDto.setOperatorId(1L);
        assignOperatorRequestDto.setGateId(1L);
        AssignOperatorResponseDto assignOperatorResponseDto = gateController.assignOperator(assignOperatorRequestDto);
        Assert.notNull(assignOperatorResponseDto, "something went wrong");
        Assert.isTrue(ResponseStatus.SUCCESS.equals(assignOperatorResponseDto.getResponseStatus()), assignOperatorResponseDto.getMessage());
        System.out.println("operator assigned to gate successfully");
    }

    @Test
    @Order(19)
    void testAssignOperatorToExitGate() {
        AssignOperatorRequestDto assignOperatorRequestDto = new AssignOperatorRequestDto();
        assignOperatorRequestDto.setOperatorId(1L);
        assignOperatorRequestDto.setGateId(2L);
        AssignOperatorResponseDto assignOperatorResponseDto = gateController.assignOperator(assignOperatorRequestDto);
        Assert.notNull(assignOperatorResponseDto, "something went wrong");
        Assert.isTrue(ResponseStatus.SUCCESS.equals(assignOperatorResponseDto.getResponseStatus()), assignOperatorResponseDto.getMessage());
        System.out.println("operator assigned to gate successfully");
    }
}
