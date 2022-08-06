package com.sanket.designparkinglot;

import com.sanket.designparkinglot.controllers.*;
import com.sanket.designparkinglot.dtos.base.response.ResponseStatus;
import com.sanket.designparkinglot.dtos.bill.CreateBillRequestDto;
import com.sanket.designparkinglot.dtos.bill.CreateBillResponseDto;
import com.sanket.designparkinglot.dtos.displayboard.CreateDisplayBoardRequestDto;
import com.sanket.designparkinglot.dtos.displayboard.CreateDisplayBoardResponseDto;
import com.sanket.designparkinglot.dtos.displayboard.GetDisplayBoardRequestDto;
import com.sanket.designparkinglot.dtos.displayboard.GetDisplayBoardResponseDto;
import com.sanket.designparkinglot.dtos.floor.*;
import com.sanket.designparkinglot.dtos.gate.*;
import com.sanket.designparkinglot.dtos.operator.CreateOperatorRequestDto;
import com.sanket.designparkinglot.dtos.operator.CreateOperatorResponseDto;
import com.sanket.designparkinglot.dtos.parkinglot.*;
import com.sanket.designparkinglot.dtos.payment.MakePaymentRequestDto;
import com.sanket.designparkinglot.dtos.payment.MakePaymentResponseDto;
import com.sanket.designparkinglot.dtos.spot.CreateSpotRequestDto;
import com.sanket.designparkinglot.dtos.spot.CreateSpotResponseDto;
import com.sanket.designparkinglot.dtos.ticket.CreateTicketRequestDto;
import com.sanket.designparkinglot.dtos.ticket.CreateTicketResponseDto;
import com.sanket.designparkinglot.dtos.vehicle.RegisterVehicleRequestDto;
import com.sanket.designparkinglot.dtos.vehicle.RegisterVehicleResponseDto;
import com.sanket.designparkinglot.models.gates.GateStatus;
import com.sanket.designparkinglot.models.gates.GateType;
import com.sanket.designparkinglot.models.payment.PaymentMode;
import com.sanket.designparkinglot.models.spot.SpotType;
import com.sanket.designparkinglot.models.vehicle.VehicleType;
import com.sanket.designparkinglot.strategies.feescalculation.FeesCalculationStrategyType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

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

    @Autowired
    private VehicleController vehicleController;

    @Autowired
    private TicketController ticketController;

    @Autowired
    private BillController billController;

    @Autowired
    private PaymentController paymentController;

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
        createSpotRequestDto.setFloorId(2L);
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
    void testCreateSpotsForMultipleFloors() {
        long totalFloors = 3;
        for (long floorId = 1; floorId <= totalFloors; floorId++) {
            // 5 spots for BIKE
            createSpots(SpotType.BIKE, 5, floorId, 1, "F" + floorId);
            // 5 spots for CAR
            createSpots(SpotType.CAR, 5, floorId, 100, "F" + floorId);
            // 2 spots for ELECTRIC CAR
            createSpots(SpotType.ELECTRIC, 2, floorId, 200, "F" + floorId);
            // 5 spots for HEAVY
            createSpots(SpotType.HEAVY, 5, floorId, 300, "F" + floorId);
            // 5 spots for PREMIUM
            createSpots(SpotType.PREMIUM, 5, floorId, 400, "F" + floorId);
        }
    }


    @Disabled
    public void createSpots(SpotType spotType, int numberOfSpots, Long floorId, int startCountId, String spotSeries) {
        for (int i = 1; i <= numberOfSpots; i++) {
            CreateSpotRequestDto createSpotRequestDto = new CreateSpotRequestDto();
            createSpotRequestDto.setSpotNumber(spotSeries + "-" + startCountId++);
            createSpotRequestDto.setSpotType(spotType);
            createSpotRequestDto.setFloorId(floorId);
            CreateSpotResponseDto createSpotResponseDto = spotController.addSpot(createSpotRequestDto);
            Assert.notNull(createSpotResponseDto, "something went wrong");
            Assert.isTrue(ResponseStatus.SUCCESS.equals(createSpotResponseDto.getResponseStatus()), createSpotResponseDto.getMessage());
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
        createGateRequestDto.setParkingLotId(2L);
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
        createGateRequestDto.setParkingLotId(2L);
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

        createOperatorRequestDto = new CreateOperatorRequestDto();
        createOperatorRequestDto.setName("operator-02");
        createOperatorResponseDto = operatorController.addOperator(createOperatorRequestDto);
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
        assignOperatorRequestDto.setOperatorId(2L);
        assignOperatorRequestDto.setGateId(2L);
        AssignOperatorResponseDto assignOperatorResponseDto = gateController.assignOperator(assignOperatorRequestDto);
        Assert.notNull(assignOperatorResponseDto, "something went wrong");
        Assert.isTrue(ResponseStatus.SUCCESS.equals(assignOperatorResponseDto.getResponseStatus()), assignOperatorResponseDto.getMessage());
        System.out.println("operator assigned to gate successfully");
    }

    @Test
    @Order(20)
    void testRegisterVehicle() {
        RegisterVehicleRequestDto registerVehicleRequestDto = new RegisterVehicleRequestDto();
        registerVehicleRequestDto.setVehicleNumber("AB01PQ1234");
        registerVehicleRequestDto.setVehicleType(VehicleType.CAR);
        RegisterVehicleResponseDto registerVehicleResponseDto = vehicleController.registerVehicle(registerVehicleRequestDto);
        Assert.notNull(registerVehicleResponseDto, "something went wrong");
        Assert.isTrue(ResponseStatus.SUCCESS.equals(registerVehicleResponseDto.getResponseStatus()), registerVehicleResponseDto.getMessage());
        System.out.println("vehicle registered successfully");
    }

    @Test
    @Order(21)
    void testReadDisplayBoard() {
        GetDisplayBoardRequestDto getDisplayBoardRequestDto = new GetDisplayBoardRequestDto();
        getDisplayBoardRequestDto.setDisplayBoardId(1L);
        GetDisplayBoardResponseDto getDisplayBoardResponseDto = displayBoardController.getDisplayBoard(getDisplayBoardRequestDto);
        Assert.notNull(getDisplayBoardResponseDto, "something went wrong");
        Assert.isTrue(ResponseStatus.SUCCESS.equals(getDisplayBoardResponseDto.getResponseStatus()), getDisplayBoardResponseDto.getMessage());
        System.out.println("current display board is " + getDisplayBoardResponseDto.getDisplayBoard().getSpotAvailability());
    }

    @Test
    @Order(22)
    void testReadFloor() {
        GetFloorRequestDto getFloorRequestDto = new GetFloorRequestDto();
        getFloorRequestDto.setFloorId(1);
        GetFloorResponseDto getFloorResponseDto = floorController.getFloorById(getFloorRequestDto);
        Assert.notNull(getFloorResponseDto, "something went wrong");
        Assert.isTrue(ResponseStatus.SUCCESS.equals(getFloorResponseDto.getResponseStatus()), getFloorResponseDto.getMessage());
        System.out.println("current floor is " + getFloorResponseDto.getFloor());
    }

    @Test
    @Order(23)
    void testGenerateTicket() {
        CreateTicketRequestDto createTicketRequestDto = new CreateTicketRequestDto();
        createTicketRequestDto.setParkingLotId(2);
        createTicketRequestDto.setVehicleId(1);
        createTicketRequestDto.setGateId(1);
        CreateTicketResponseDto createTicketResponseDto = ticketController.createTicket(createTicketRequestDto);
        Assert.notNull(createTicketResponseDto, "something went wrong");
        Assert.isTrue(ResponseStatus.SUCCESS.equals(createTicketResponseDto.getResponseStatus()), createTicketResponseDto.getMessage());
        System.out.println("generated ticket is " + createTicketResponseDto.getTicket());
    }

    @Test
    @Order(24)
    void testGenerateBill() {
        CreateBillRequestDto createBillRequestDto = new CreateBillRequestDto();
        createBillRequestDto.setTicketId(1);
        createBillRequestDto.setGateId(2);
        createBillRequestDto.setFeesCalculationStrategyType(FeesCalculationStrategyType.NORMAL);
        CreateBillResponseDto createBillResponseDto = billController.createBill(createBillRequestDto);
        Assert.notNull(createBillResponseDto, "something went wrong");
        Assert.isTrue(ResponseStatus.SUCCESS.equals(createBillResponseDto.getResponseStatus()), createBillResponseDto.getMessage());
        System.out.println("generated bill is " + createBillResponseDto.getBill());
    }

    @Test
    @Order(25)
    void testMakePayment() {
        MakePaymentRequestDto makePaymentRequestDto = new MakePaymentRequestDto();
        makePaymentRequestDto.setPaymentMode(PaymentMode.UPI);
        makePaymentRequestDto.setBillId(1);
        MakePaymentResponseDto makePaymentResponseDto = paymentController.makePayment(makePaymentRequestDto);
        Assert.notNull(makePaymentResponseDto, "something went wrong");
        Assert.isTrue(ResponseStatus.SUCCESS.equals(makePaymentResponseDto.getResponseStatus()), makePaymentResponseDto.getMessage());
        System.out.println("payment " + makePaymentResponseDto.getPayment());
    }
}
