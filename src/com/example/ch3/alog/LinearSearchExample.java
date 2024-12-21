package com.example.ch3.alog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Array creation time: 72777209 ns
 * Search execution time: 11102666 ns
 */

//Array creation time: 20502500 ns
//Search execution time: 16775600 ns

//Array creation time: 20170500 ns
//Search execution time: 17324900 ns



class LinearSearchExample {
    public static void main(String[] args) {
        int size = 1_000_000;

        //배열 생성 식나 측정
        long creationStartTime = System.nanoTime();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * 100); //0~99 범위의
        }
        long creationEndTime = System.nanoTime();

        int[] targets = {42, 50, 62}; //찾고자 하는 여러 값

        //탐색 시간 측정
        long searchStartTime = System.nanoTime();
        Map<Integer, List<Integer>> results = findIndicesForTarget(array, targets);
        long searchEndTime = System.nanoTime();

        //결과 출력
        printResultsWithValues(array, results);

        //시간 출력
        System.out.println("Array creation time: " + (creationEndTime - creationStartTime) + " ns");
        System.out.println("Search execution time: " + (searchEndTime - searchStartTime) + " ns");
    }

    public static Map<Integer, List<Integer>> findIndicesForTarget(int[] array, int[] targets) {
        Map<Integer, List<Integer>> results = new HashMap<>();
        for (int target : targets) {
            results.put(target, new ArrayList<>());
        }
        for(int i = 0; i < array.length; i++) {
            if (results.containsKey(array[i])) {
                results.get(array[i]).add(i);
            }
        }
        return results;
    }

    public static void printResultsWithValues(int[] array, Map<Integer, List<Integer>> results) {
        for (Map.Entry<Integer, List<Integer>> entry : results.entrySet()) {
            int target = entry.getKey();
            List<Integer> indices = entry.getValue();
            if (indices.isEmpty()) {
                System.out.println("Target " + target + " not found.");
            } else {
                System.out.println("Target " + target + " found at indices: ");
                for (int index : indices) {
                    System.out.println("Index: " + index + ", Value: " + array[index]);
                }
            }
        }
    }
}
