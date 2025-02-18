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
    public static Function<Order, Order> calculateShipping(int baseFee, int extraFeePerItem) {
        return order -> {
            order.shipping = baseFee + (extraFeePerItem * order.quantity);

            return order;
        };
    }

    // 3) 포인트 적립

    public static Function<Order, Order> accumulatePoints(double pointValue) {
        return order -> {
            double totalPriceAfterDiscount = (order.price * order.quantity) - order.discount;
            double totalCost = totalPriceAfterDiscount + order.shipping;

            order.points = (int)(totalCost * pointValue);

            return order;
        };
    }
}

