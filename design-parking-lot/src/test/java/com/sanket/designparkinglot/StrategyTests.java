package com.sanket.designparkinglot;

import com.sanket.designparkinglot.factories.feescalculationstrategyfactory.FeesCalculationStrategyFactory;
import com.sanket.designparkinglot.factories.paymentstrategyfactory.PaymentStrategyFactory;
import com.sanket.designparkinglot.factories.spotassignmentstrategyfactory.SpotAssignmentStrategyFactory;
import com.sanket.designparkinglot.models.bill.Bill;
import com.sanket.designparkinglot.models.payment.Payment;
import com.sanket.designparkinglot.models.payment.PaymentMode;
import com.sanket.designparkinglot.strategies.spotassignment.SpotAssignmentStrategyType;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StrategyTests {

    private final FeesCalculationStrategyFactory feesCalculationStrategyFactory;

    private final PaymentStrategyFactory paymentStrategyFactory;

    private final SpotAssignmentStrategyFactory spotAssignmentStrategyFactory;

    @Autowired
    public StrategyTests(FeesCalculationStrategyFactory feesCalculationStrategyFactory, PaymentStrategyFactory paymentStrategyFactory, SpotAssignmentStrategyFactory spotAssignmentStrategyFactory) {
        this.feesCalculationStrategyFactory = feesCalculationStrategyFactory;
        this.paymentStrategyFactory = paymentStrategyFactory;
        this.spotAssignmentStrategyFactory = spotAssignmentStrategyFactory;
    }

    @Test
    @Order(1)
    void testInitializeStrategies() {
        System.out.println(feesCalculationStrategyFactory);
        System.out.println(paymentStrategyFactory);
        System.out.println(spotAssignmentStrategyFactory);
    }
}
