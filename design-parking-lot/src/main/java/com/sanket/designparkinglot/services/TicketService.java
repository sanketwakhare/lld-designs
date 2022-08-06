package com.sanket.designparkinglot.services;

import com.sanket.designparkinglot.exceptions.*;
import com.sanket.designparkinglot.factories.SpotAssignmentStrategyFactory;
import com.sanket.designparkinglot.models.gates.EntryGate;
import com.sanket.designparkinglot.models.gates.Gate;
import com.sanket.designparkinglot.models.gates.GateType;
import com.sanket.designparkinglot.models.parkinglot.ParkingLot;
import com.sanket.designparkinglot.models.spot.Spot;
import com.sanket.designparkinglot.models.spot.SpotStatus;
import com.sanket.designparkinglot.models.ticket.Ticket;
import com.sanket.designparkinglot.models.vehicle.Vehicle;
import com.sanket.designparkinglot.repositories.*;
import com.sanket.designparkinglot.strategies.spotassignment.SpotAssignmentStrategy;
import com.sanket.designparkinglot.strategies.spotassignment.SpotAssignmentStrategyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public class TicketService extends BaseService {

    private final ParkingLotRepository parkingLotRepository;

    private final GateRepository gateRepository;

    private final VehicleRepository vehicleRepository;

    private final TicketRepository ticketRepository;

    private final SpotRepository spotRepository;

    private final SpotAssignmentStrategyFactory spotAssignmentStrategyFactory;

    @Autowired
    public TicketService(ParkingLotRepository parkingLotRepository, GateRepository gateRepository, VehicleRepository vehicleRepository, TicketRepository ticketRepository, SpotRepository spotRepository, SpotAssignmentStrategyFactory spotAssignmentStrategyFactory) {
        this.parkingLotRepository = parkingLotRepository;
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.ticketRepository = ticketRepository;
        this.spotRepository = spotRepository;
        this.spotAssignmentStrategyFactory = spotAssignmentStrategyFactory;
    }

    public Ticket createTicket(long parkingLotId, long entryGateId, long vehicleId, SpotAssignmentStrategyType spotAssignmentStrategyType) throws NoParkingLotException, NoGateException, InvalidEntryGateException, NoVehiclePresentException, NoSpotAvailableException {

        SpotAssignmentStrategy spotAssignmentStrategy = spotAssignmentStrategyFactory.get(spotAssignmentStrategyType);

        // get parking lot by id
        Optional<ParkingLot> dbParkingLot = parkingLotRepository.findById(parkingLotId);
        if (dbParkingLot.isEmpty()) {
            throw new NoParkingLotException(parkingLotId);
        }
        // get entry gate by id
        Optional<Gate> dbGate = gateRepository.findById(entryGateId);
        if (dbGate.isEmpty()) {
            throw new NoGateException(entryGateId);
        }
        if (!GateType.ENTRY.equals(dbGate.get().getGateType())) {
            throw new InvalidEntryGateException(entryGateId);
        }
        // get vehicle by id
        Optional<Vehicle> dbVehicle = vehicleRepository.findById(vehicleId);
        if (dbVehicle.isEmpty()) {
            throw new NoVehiclePresentException(vehicleId);
        }

        ParkingLot parkingLot = dbParkingLot.get();
        EntryGate gate = (EntryGate) dbGate.get();
        Vehicle vehicle = dbVehicle.get();

        // assign spot
        Spot spot = spotAssignmentStrategy.assignSpot(parkingLot, vehicle, gate);
        spot.setSpotStatus(SpotStatus.OCCUPIED);
        // save spot
        Spot dbSpot = spotRepository.save(spot);

        // create ticket
        Date currentTimeStamp = Calendar.getInstance().getTime();
        Ticket ticket = new Ticket();
        ticket.setParkingLot(parkingLot);
        ticket.setEntryGate(gate);
        ticket.setVehicle(vehicle);
        ticket.setEntryTime(currentTimeStamp);
        ticket.setOperator(gate.getOperator());
        ticket.setSpot(dbSpot);
        ticket.setFloor(dbSpot.getFloor());
        setCreateModelDefaults(ticket);

        // save ticket
        return ticketRepository.save(ticket);
    }
}
