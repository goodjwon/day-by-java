package com.example.ch4.functions.hw;

import java.util.function.Function;

public class InventoryCheck {
    public static void main(String[] args) {

        int minRequired = 5;

        Function<Integer, Boolean> isStockInsufficient = s -> s >= minRequired;

        int[] stockQuantities = {2};

        for (int stock : stockQuantities) {
            boolean insufficient = isStockInsufficient.apply(stock);
            System.out.println(insufficient ? "true" : "false");
        }
    }
}
