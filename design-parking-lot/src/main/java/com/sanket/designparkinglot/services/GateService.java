package com.sanket.designparkinglot.services;

import com.sanket.designparkinglot.exceptions.GateCreationException;
import com.sanket.designparkinglot.exceptions.InvalidEntryGateException;
import com.sanket.designparkinglot.exceptions.NoDisplayBoardException;
import com.sanket.designparkinglot.exceptions.NoGateException;
import com.sanket.designparkinglot.models.displayboard.DisplayBoard;
import com.sanket.designparkinglot.models.gates.*;
import com.sanket.designparkinglot.repositories.DisplayBoardRepository;
import com.sanket.designparkinglot.repositories.GateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GateService extends BaseService {

    private final GateRepository gateRepository;

    private final DisplayBoardRepository displayBoardRepository;

    @Autowired
    public GateService(GateRepository gateRepository, DisplayBoardRepository displayBoardRepository) {
        this.gateRepository = gateRepository;
        this.displayBoardRepository = displayBoardRepository;
    }

    public Gate addGate(String gateNumber, GateType gateType, GateStatus gateStatus) throws GateCreationException {

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
        setCreateModelDefaults(gate);
        return gateRepository.save(gate);
    }

    public Gate modifyGateStatus(Long gateId, GateStatus gateStatus) throws NoGateException {
        Optional<Gate> dbGate = gateRepository.findById(gateId);
        if (dbGate.isEmpty()) {
            throw new NoGateException(gateId);
        }
        Gate gate = dbGate.get();
        gate.setGateStatus(gateStatus);
        setUpdateModelDefaults(gate);
        return gateRepository.save(gate);
    }

    public EntryGate assignDisplayBoard(Long gateId, Long displayBoardId) throws NoGateException, InvalidEntryGateException, NoDisplayBoardException {
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

        // save owner entity: entry gate
        entryGate.setDisplayBoard(displayBoard);
        setUpdateModelDefaults(entryGate);
        return gateRepository.save(entryGate);
    }
}
