package com.sanket.designparkinglot.factories;

import com.sanket.designparkinglot.models.payment.PaymentMode;
import com.sanket.designparkinglot.strategies.paymentstrategy.PaymentStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class PaymentStrategyFactory {

    private final Map<PaymentMode, PaymentStrategy> registry;

    @Autowired
    public PaymentStrategyFactory(Set<PaymentStrategy> strategies) {
        // spring boot will inject all the strategies into set of strategies here
        // we can also use map of bean name to strategy bean. Map<String, PaymentStrategy>
        registry = new HashMap<>();
        strategies.forEach(strategy -> {
            PaymentMode strategyType = strategy.getStrategyType();
            register(strategyType, strategy);
        });
    }

    public PaymentStrategy get(PaymentMode paymentMode) {
        return registry.get(paymentMode);
    }

    public void register(PaymentMode paymentMode, PaymentStrategy paymentStrategy) {
        registry.put(paymentMode, paymentStrategy);
    }
}
