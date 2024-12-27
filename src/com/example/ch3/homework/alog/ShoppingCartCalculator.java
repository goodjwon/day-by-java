package com.example.ch3.homework.alog;

public class ShoppingCartCalculator {
    public static void main(String[] args) {
        double[][] cart = {
                {100.0, 2},  // 상품 가격 100, 수량 2
                {50.0, 1},   // 상품 가격 50, 수량 1
                {200.0, 1}   // 상품 가격 200, 수량 1
        };
        double discountRate = 10.0; // 10% 할인

        double total = calculate(cart, discountRate);

        System.out.printf("최종 금액: %.2f\n", total);
    }

    public static double calculate(double[][] cart, double discountRate) {
        double total = 0.0;

        for (double[] item : cart) {
            total += item[0] * item[1];
        }

        double discount = total * (discountRate / 100.0);
        total -= discount;

        return total;
    }
}
