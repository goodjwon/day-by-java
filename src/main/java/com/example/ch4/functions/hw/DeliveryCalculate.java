package com.example.ch4.functions.hw;

import java.util.function.Function;

public class DeliveryCalculate {
    public static void main(String[] args) {

        int quantity = 5; //qty

        int baseFee = 3000; // 상수.
        int extraFeePerItem = 500; // 상수.

        Function<Integer, Integer> shippingCostFunction
                = pty -> baseFee + (pty * extraFeePerItem);

        int totalShippingCost = shippingCostFunction.apply(quantity);

        System.out.println(totalShippingCost);

//        List<Integer> delivery = Arrays.asList(5);
//
//        Function<Integer, Integer> intFunction = s -> {
//            Integer result = Integer.parseInt(String.valueOf(s));
//            int sale = 3000 + (result * 500);
//            System.out.println("배송비: " + sale);
//
//            return result;
//        };
//
//        delivery.forEach(s -> intFunction.apply(s));
    }
}
