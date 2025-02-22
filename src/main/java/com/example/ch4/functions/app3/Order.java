package com.example.ch4.functions.app3;

/**
 * order domain entity bean
 * 참고. bean, pojo, DTO(Data Transfer Object)
 */
public class Order {
    private final double unitPrice; // 상품 단가
    private final int quantity;     // 구매 수량

    private double discount; // 할인 금액
    private double shipping; // 배송비
    private int points;      // 적립 포인트

    public Order(double unitPrice, int quantity) {
        if (unitPrice < 0) {
            throw new IllegalArgumentException("단가는 음수가 될 수 없습니다.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("수량은 0보다 커야 합니다.");
        }

        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    // 할인 적용: (단가 * 수량)에 rate% 만큼 할인
    public Order applyDiscount(double rate) {
        double totalPrice = unitPrice * quantity;
        discount = totalPrice * rate;

        return this;
    }

    // 배송비 계산: 기본 배송비 + (수량당 배송비)
    public Order calculateShipping(int baseFee, int extraFeePerItem) {
        shipping = baseFee + (extraFeePerItem * quantity);

        return this;
    }

    // 포인트 적립: (총금액 - 할인 + 배송비)에 pointRate 적용
    public Order accumulatePoints(double pointValue) {
        double totalPriceAfterDiscount = (unitPrice * quantity) - discount;
        double totalCost = totalPriceAfterDiscount + shipping;

        points = (int)(totalCost * pointValue);

        return this;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public double getShipping() {
        return shipping;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return String.format(
                "Order{ unitPrice=%.2f, quantity=%d, discount=%.2f, shipping=%.2f, points=%d }",
                unitPrice, quantity, discount, shipping, points
        );
    }
}
