package com.sanket.designparkinglot.services;

import com.sanket.designparkinglot.exceptions.InvalidEntryGateException;
import com.sanket.designparkinglot.exceptions.InvalidExitGateException;
import com.sanket.designparkinglot.exceptions.NoGateException;
import com.sanket.designparkinglot.exceptions.NoTicketException;
import com.sanket.designparkinglot.factories.FeesCalculationStrategyFactory;
import com.sanket.designparkinglot.models.bill.Bill;
import com.sanket.designparkinglot.models.bill.BillPaymentStatus;
import com.sanket.designparkinglot.models.gates.ExitGate;
import com.sanket.designparkinglot.models.gates.Gate;
import com.sanket.designparkinglot.models.gates.GateType;
import com.sanket.designparkinglot.models.ticket.Ticket;
import com.sanket.designparkinglot.repositories.BillRepository;
import com.sanket.designparkinglot.repositories.GateRepository;
import com.sanket.designparkinglot.repositories.TicketRepository;
import com.sanket.designparkinglot.strategies.feescalculation.FeesCalculationStrategy;
import com.sanket.designparkinglot.strategies.feescalculation.FeesCalculationStrategyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class BillService extends BaseService {
    private final BillRepository billRepository;

    private final GateRepository gateRepository;

    private final TicketRepository ticketRepository;

    private final FeesCalculationStrategyFactory feesCalculationStrategyFactory;

    @Autowired
    public BillService(BillRepository billRepository, GateRepository gateRepository, TicketRepository ticketRepository, FeesCalculationStrategyFactory feesCalculationStrategyFactory) {
        this.billRepository = billRepository;
        this.gateRepository = gateRepository;
        this.ticketRepository = ticketRepository;
        this.feesCalculationStrategyFactory = feesCalculationStrategyFactory;
    }

    public Bill createBill(long gateId, long ticketId, FeesCalculationStrategyType feesCalculationStrategyType) throws NoGateException, InvalidExitGateException, NoTicketException {

        FeesCalculationStrategy feesCalculationStrategy = feesCalculationStrategyFactory.get(feesCalculationStrategyType);

        Optional<Gate> dbGate = gateRepository.findById(gateId);
        if (dbGate.isEmpty()) {
            throw new NoGateException(gateId);
        }
        if (!GateType.EXIT.equals(dbGate.get().getGateType())) {
            throw new InvalidExitGateException(gateId);
        }

        Optional<Ticket> dbTicket = ticketRepository.findById(ticketId);
        if (dbTicket.isEmpty()) {
            throw new NoTicketException(ticketId);
        }

        ExitGate gate = (ExitGate) dbGate.get();
        Ticket ticket = dbTicket.get();

        // calculate fees
        double fees = feesCalculationStrategy.calculateFees(ticket);

        // create bill
        Date currentTimeStamp = Calendar.getInstance().getTime();
        Bill bill = new Bill();
        bill.setTicket(ticket);
        bill.setExitGate(gate);
        bill.setOperator(gate.getOperator());
        bill.setBillPaymentStatus(BillPaymentStatus.UNPAID);
        bill.setCharges(fees);
        bill.setExitTime(currentTimeStamp);
        setCreateModelDefaults(bill);

        // save bill
        return billRepository.save(bill);
    }
}
