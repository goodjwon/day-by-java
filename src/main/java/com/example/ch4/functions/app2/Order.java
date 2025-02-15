package com.example.ch4.functions.app2;

public class Order {
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
