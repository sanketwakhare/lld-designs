package com.sanket.designparkinglot.strategies.feescalculation;

import com.sanket.designparkinglot.models.spot.SpotType;
import com.sanket.designparkinglot.models.ticket.Ticket;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class NormalFeesCalculationStrategy implements FeesCalculationStrategy {

    private final Map<SpotType, Double> feeStructure;

    public NormalFeesCalculationStrategy() {
        Map<SpotType, Double> feeStructure = new HashMap<>();
        feeStructure.put(SpotType.BIKE, 50.0);
        feeStructure.put(SpotType.CAR, 80.0);
        feeStructure.put(SpotType.HEAVY, 120.0);
        feeStructure.put(SpotType.ELECTRIC, 100.0);
        feeStructure.put(SpotType.PREMIUM, 200.0);
        this.feeStructure = feeStructure;
    }

    @Override
    public FeesCalculationStrategyType getStrategyType() {
        return FeesCalculationStrategyType.NORMAL;
    }

    @Override
    public double calculateFees(Ticket ticket) {
        Date entryTime = ticket.getEntryTime();
        Date exitTime = Calendar.getInstance().getTime();
        long diff = exitTime.getTime() - entryTime.getTime();
        long differenceInHours
                = (diff
                / (1000 * 60 * 60)) % 24;
        return (Math.floor(differenceInHours) + 1) * feeStructure.get(ticket.getSpot().getSpotType());
    }
}
