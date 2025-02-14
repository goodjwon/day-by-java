package com.example.ch4.functions.hw;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class DeliveryCalculate {
    public static void main(String[] args) {

        List<Integer> delivery = Arrays.asList(5);

        Function<Integer, Integer> intFunction = s -> {
            Integer result = Integer.parseInt(String.valueOf(s));
            int sale = 3000 + (result * 500);
            System.out.println("배송비: " + sale);

            return result;
        };

        delivery.forEach(s -> intFunction.apply(s));
    }
}
