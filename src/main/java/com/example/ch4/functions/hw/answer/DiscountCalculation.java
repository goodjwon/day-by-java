package com.example.ch4.functions.hw.answer;

import java.util.function.Function;

public class DiscountCalculation {
    public static void main(String[] args) {
        double originalPrice = 10071.6;
        double discountRate = 0.13;

        // 할인을 계산하는 함수, 할인된 가격.. price * discount => 10000.0 * 0.1 => 1000 => 9000.0
        Function<Double, Double> discountFunction = price -> price - (price * discountRate);

        // 반올림.
        Function<Double, Double> roundFunction = val -> Math.round(val * 100) / 100.0;
        // 올림 double roundedUp = Math.ceil(val * 100.0) / 100.0;
        // 내림 double roundedDown = Math.floor(val * 100.0) / 100.0;

        Function<Double, Double> finalCalculation = discountFunction.andThen(roundFunction);

        double result = finalCalculation.apply(originalPrice);

        System.out.println(result);
    }

}
