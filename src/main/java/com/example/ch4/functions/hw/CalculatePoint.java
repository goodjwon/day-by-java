package com.example.ch4.functions.hw;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class CalculatePoint {
    public static void main(String[] args) {
        List<Long> point = Arrays.asList(15000L);

        Function<Long, Integer> intFunction = s -> {
            Integer result = Integer.parseInt(String.valueOf(s));
            int accumulation = result / 100;
            System.out.println("포인트: " + accumulation + "(포인트)");

            return result;
        };

        point.forEach(s -> intFunction.apply(s));
    }
}
