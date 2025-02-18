package com.example.ch4.functions.hw;

import java.util.function.Function;

//answer.DiscountCalculation
public class SalePayMount {
    public static void main(String[] args) {
        double basePrice = 10000.0;
        double discount = 0.1;

        Function<Double, Double> discountFunction = price -> price - (price * discount);

        Function<Double, Double> roundFunction = val -> Math.round(val * 100) / 100.0;

        Function<Double, Double> finalFunction = discountFunction.andThen(roundFunction);

        double result = finalFunction.apply(basePrice);

        System.out.println(result);
    }
}
