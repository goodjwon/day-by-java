package com.example.ch4.functions.app2;

import java.util.function.Function;

public class OrderPipeline {
    private Function<Order, Order> pipeline;

    public OrderPipeline(Function<Order, Order> pipeline) {
        this.pipeline = pipeline;
    }

    public Order process(Order myOrder) {
        return pipeline.apply(myOrder);
    }
}
