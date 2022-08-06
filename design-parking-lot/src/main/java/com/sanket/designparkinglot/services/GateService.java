package com.sanket.designparkinglot.services;

import com.sanket.designparkinglot.exceptions.*;
import com.sanket.designparkinglot.models.displayboard.DisplayBoard;
import com.sanket.designparkinglot.models.gates.*;
import com.sanket.designparkinglot.models.operator.Operator;
import com.sanket.designparkinglot.models.parkinglot.ParkingLot;
import com.sanket.designparkinglot.repositories.DisplayBoardRepository;
import com.sanket.designparkinglot.repositories.GateRepository;
import com.sanket.designparkinglot.repositories.OperatorRepository;
import com.sanket.designparkinglot.repositories.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class GateService extends BaseService {

    private final GateRepository gateRepository;

    private final DisplayBoardRepository displayBoardRepository;

    private final OperatorRepository operatorRepository;

    private final ParkingLotRepository parkingLotRepository;

    @Autowired
    public GateService(GateRepository gateRepository, DisplayBoardRepository displayBoardRepository, OperatorRepository operatorRepository, ParkingLotRepository parkingLotRepository) {
        this.gateRepository = gateRepository;
        this.displayBoardRepository = displayBoardRepository;
        this.operatorRepository = operatorRepository;
        this.parkingLotRepository = parkingLotRepository;
    }

    public Gate addGate(String gateNumber, GateType gateType, GateStatus gateStatus, long parkingLotId) throws GateCreationException, NoParkingLotException {

        Optional<ParkingLot> dbParkingLot = parkingLotRepository.findById(parkingLotId);
        if (dbParkingLot.isEmpty()) {
            throw new NoParkingLotException(parkingLotId);
        }
        ParkingLot parkingLot = dbParkingLot.get();

        Gate gate;
        if (GateType.ENTRY.equals(gateType)) {
            gate = new EntryGate();
        } else if (GateType.EXIT.equals(gateType)) {
            gate = new ExitGate();
        } else {
            throw new GateCreationException(gateNumber);
        }

        gate.setGateNumber(gateNumber);
        gate.setGateStatus(gateStatus);
        gate.setParkingLot(parkingLot);
        setCreateModelDefaults(gate);

        return gateRepository.save(gate);
    }

    public Gate modifyGateStatus(long gateId, GateStatus gateStatus) throws NoGateException {
        Optional<Gate> dbGate = gateRepository.findById(gateId);
        if (dbGate.isEmpty()) {
            throw new NoGateException(gateId);
        }
        Gate gate = dbGate.get();
        gate.setGateStatus(gateStatus);
        setUpdateModelDefaults(gate);
        return gateRepository.save(gate);
    }

    public EntryGate assignDisplayBoard(long gateId, long displayBoardId) throws NoGateException, InvalidEntryGateException, NoDisplayBoardException {
        // fetch gate by id
        Optional<Gate> dbGate = gateRepository.findById(gateId);
        if (dbGate.isEmpty()) {
            throw new NoGateException(gateId);
        }
        if (GateType.EXIT.equals(dbGate.get().getGateType())) {
            throw new InvalidEntryGateException("display board cannot be assigned to exit gate", gateId);
        }
        EntryGate entryGate = (EntryGate) dbGate.get();

        // fetch DisplayBoard by id
        Optional<DisplayBoard> dbDisplayBoard = displayBoardRepository.findById(displayBoardId);
        if (dbDisplayBoard.isEmpty()) {
            throw new NoDisplayBoardException(displayBoardId);
        }
        DisplayBoard displayBoard = dbDisplayBoard.get();

        // de-associate current display board from previously assigned gate
        EntryGate oldGate = displayBoard.getEntryGate();
        if (!Objects.isNull(oldGate)) {
            oldGate.setDisplayBoard(null);
            setUpdateModelDefaults(oldGate);
            gateRepository.save(oldGate);
        }

        // set new entry gate
        displayBoard.setEntryGate(entryGate);
        // save owner entity: entry gate
        entryGate.setDisplayBoard(displayBoard);
        setUpdateModelDefaults(entryGate);
        return gateRepository.save(entryGate);
    }

    public Gate assignOperator(long gateId, long operatorId) throws NoGateException, NoOperatorException {
        // fetch gate by id
        Optional<Gate> dbGate = gateRepository.findById(gateId);
        if (dbGate.isEmpty()) {
            throw new NoGateException(gateId);
        }
        Gate gate = dbGate.get();

        // fetch Operator by id
        Optional<Operator> dbOperator = operatorRepository.findById(operatorId);
        if (dbOperator.isEmpty()) {
            throw new NoOperatorException(operatorId);
        }
        Operator operator = dbOperator.get();

        // de-associate current operator from previously assigned gate
        Gate oldGate = operator.getGate();
        if (!Objects.isNull(oldGate)) {
            oldGate.setOperator(null);
            setUpdateModelDefaults(oldGate);
            gateRepository.save(oldGate);
        }
        operator.setGate(gate);

        // save owner entity: gate
        gate.setOperator(operator);
        setUpdateModelDefaults(gate);
        return gateRepository.save(gate);
    }
}
