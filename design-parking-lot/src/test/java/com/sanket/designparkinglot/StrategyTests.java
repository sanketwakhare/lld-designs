package com.sanket.designparkinglot;

import com.sanket.designparkinglot.factories.paymentstrategyfactory.PaymentStrategyFactory;
import com.sanket.designparkinglot.strategies.feescalculator.FeesCalculatorStrategy;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StrategyTests {

    private final FeesCalculatorStrategy feesCalculatorStrategy;

    private final PaymentStrategyFactory paymentStrategyFactory;

    @Autowired
    public StrategyTests(FeesCalculatorStrategy feesCalculatorStrategy, PaymentStrategyFactory paymentStrategyFactory) {
        this.feesCalculatorStrategy = feesCalculatorStrategy;
        this.paymentStrategyFactory = paymentStrategyFactory;
    }

    @Test
    @Order(1)
    void testStrategy() {
        System.out.println(feesCalculatorStrategy);
        System.out.println(paymentStrategyFactory);
    }
}
