package com.example.ch3.homework.alog;

import java.util.HashMap;
import java.util.Map;

public class PurchaseFrequencyAnalyzer {
    public static void main(String[] args) {
        int[] purchaseHistory = {101, 102, 101, 103, 102, 101};

        System.out.println("가장 많이 구매한 상품 ID: " + findFrequency(purchaseHistory));
    }

    public static int findFrequency(int[] purchaseHistory) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int ProductId = -1;
        int maxFrequency = 0;

        for (int productId : purchaseHistory) {
            int frequency = countMap.getOrDefault(productId, 0) + 1;
            countMap.put(productId, frequency);
            if (frequency > maxFrequency || (frequency == maxFrequency && productId < ProductId)) {
                ProductId = productId;
                maxFrequency = frequency;
            }
        }

        return ProductId;
    }
}
