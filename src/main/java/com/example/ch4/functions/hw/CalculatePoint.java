package com.example.ch4.functions.hw;

import java.util.function.Function;


public class CalculatePoint {
    public static void main(String[] args) {
        long basePoint = 15000L;
        double AccumulatePoint = 0.01;

        Function<Long, Integer> AccumulateFunction = point -> (int)(point * AccumulatePoint);

        int result = AccumulateFunction.apply(basePoint);

        System.out.println(result);
    }
}
