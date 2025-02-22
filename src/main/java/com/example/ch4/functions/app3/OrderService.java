package com.example.ch4.functions.app3;

import java.util.function.Function;

public class OrderService {
    private final OrderProperties properties;

    public OrderService(OrderProperties properties) {
        this.properties = properties;
    }

    public Order processOrder(Order order){
        Function<Order, Order> orderPipline = ((Function<Order, Order>)
                (o->o.applyDiscount(properties.getDiscountRate())))
                .andThen(o->o.calculateShipping(properties.getBaseShippingFee(), properties.getPerItemShippingFee()))
                .andThen(o->o.accumulatePoints(properties.getPointRate()));

        return orderPipline.apply(order);
    }

}
