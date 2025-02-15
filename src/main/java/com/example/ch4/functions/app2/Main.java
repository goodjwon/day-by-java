package com.example.ch4.functions.app2;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.Consumer;


public class Main {
    public static void main(String[] args) {
        // 1) Order Supplier: 재사용할 수 있는 생성 로직
        Supplier<Order> orderSupplier = () -> new Order(10000.0, 5);

        // 2) 필요한 로직(할인, 배송비, 포인트)을 정적으로 가져와 체이닝
        //    - 10% 할인, 기본 배송비 3000 + 개당 500, 포인트 1%
        Function<Order, Order> pipeline = OrderFunctions.applyDiscount(0.1)
                .andThen(OrderFunctions.calculateShipping(3000, 500))
                .andThen(OrderFunctions.accumulatePoints(0.01));

        // 3) OrderPipeline 객체로 감싸서 관리하거나, 그냥 pipeline.apply() 호출해도 됨
        OrderPipeline orderPipeline = new OrderPipeline(pipeline);

        // 4) 주문 생성
        Order myOrder = orderSupplier.get();

        // 5) 파이프라인 처리
        Order finalOrder = orderPipeline.process(myOrder);

        // 6) 후처리 로직(Consumer): 최종 결과 로그 출력 (체이닝 후 확장 가능)
        Consumer<Order> logOrder = order -> {
            System.out.println("[최종 주문 정보] " + order);
        };

        // 7) Consumer 실행
        logOrder.accept(finalOrder);
    }
}
