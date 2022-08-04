package com.sanket.designparkinglot.services;

import com.sanket.designparkinglot.exceptions.GateCreationException;
import com.sanket.designparkinglot.exceptions.NoGateException;
import com.sanket.designparkinglot.models.gates.*;
import com.sanket.designparkinglot.repositories.GateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GateService extends BaseService {

    private final GateRepository gateRepository;

    @Autowired
    public GateService(GateRepository gateRepository) {
        this.gateRepository = gateRepository;
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
}
