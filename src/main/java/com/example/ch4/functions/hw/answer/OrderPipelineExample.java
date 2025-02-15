package com.example.ch4.functions.hw.answer;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

// Order domain. 업무의 최소 유닛 단위. >> DDD (도메인 주도 개발)
class Order {
    double price;     // 상품 가격
    int quantity;     // 구매 수량
    double discount;  // 할인 금액
    double shipping;  // 배송비
    int points;       // 적립 포인트

    public Order(double price, int quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "price=" + price +
                ", quantity=" + quantity +
                ", discount=" + discount +
                ", shipping=" + shipping +
                ", points=" + points +
                '}';
    }
}

/**
 *
 간단한 주문( Order ) 객체를 예시로, 다음 과정을 거치는 Fluent 스타일 파이프라인을 구성
 주문 생성 (Supplier)
 할인 적용 (Function)
 배송비 계산 (Function)
 포인트 적립 (Function)
 최종 로그 출력 (Consumer)
 */

//간략화버전
public class OrderPipelineExample {
    public static void main(String[] args) {

        // 1) 주문 생성 (Supplier)
        //    - 실제 환경에서는 사용자 입력, DB 조회 등으로 데이터를 가져온다고 가정
        // orderSupplier

        Supplier<Order> orderSupplier = () -> new Order(10000.0, 5);

        // 2) 할인 적용 (Function)
        //    - 이전 예시 '상품 할인 계산'과 유사, 가격 * 수량에 할인율 10% 적용
        // applyDiscount
        Function<Order, Order> applyDiscount = order ->{
            double rate = 0.1;
            double totalPrice = order.price * order.quantity;
            double discountAmount = totalPrice * rate;
            order.discount = discountAmount;

            return  order;
        };

        // 3) 배송비 계산 (Function)
        //    - 이전 예시 '배송비 산정'과 유사, 기본 배송비 3000 + 개당 500
        // calculateShipping
        Function<Order, Order> calculateShipping = order -> {
            int baseFee = 3000;
            int extraFeePerItem = 500;
            order.shipping = baseFee + (extraFeePerItem * order.quantity);

            return order;
        };

        // 4) 포인트 적립 (Function)
        //    - 이전 예시 '결제 금액 포인트 환산'과 유사
        //    - 여기서는 (상품 총액 - 할인 + 배송비)의 1% 포인트라고 가정
        // accumulatePoints
        Function<Order, Order> accumulatePoints = order -> {
            double totalPriceAfterDiscount = (order.price * order.quantity) - order.discount;
            double totalCost = totalPriceAfterDiscount + order.shipping;

            order.points = (int)(totalCost * 0.01);

            return order;

        };

        // 체이닝: applyDiscount -> calculateShipping -> accumulatePoints
        // orderPipeline
        Function<Order, Order> orderPipeline
                = applyDiscount
                    .andThen(calculateShipping)
                    .andThen(accumulatePoints);

        Order myOrder = orderSupplier.get();

        Order finalOrder = orderPipeline.apply(myOrder);

        Consumer<Order> logOrder = order -> {
            System.out.println("[최종 주문 정보]" + order);
        };

        logOrder.accept(finalOrder);
    }
}
