package com.example.ch4.functions.app2;


import java.util.function.Function;

public class OrderFunctions {
    // 1) 할인 적용
    //    - (가격 * 수량)의 rate% 만큼 discount 필드에 기록
    public static Function<Order, Order> applyDiscount(double rate) {
        return order -> {
            double totalPrice = order.price * order.quantity;
            double discountAmount = totalPrice * rate;
            order.discount = discountAmount;

            return order;
        };
    }

    // 2) 배송비 계산

    // 3) 포인트 적립
}

