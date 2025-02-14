package com.example.ch4.functions.hw;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class SalePayMount {
    public static void main(String[] args) {

        List<Double> sales = Arrays.asList(10000.0);

        Function<Double, Double> doubleFunction = s -> {
            Double result = Double.parseDouble(String.valueOf(s));
            Double sale = result / 10;
            System.out.println(result - sale);

            return result;
        };

        sales.forEach(s -> doubleFunction.apply(s));
    }
}
