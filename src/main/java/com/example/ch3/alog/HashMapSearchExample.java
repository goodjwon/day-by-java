package com.example.ch3.alog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 HashMap creation time: 36883625 ns
 Search execution time: 46458 ns
 */

//HashMap creation time: 44630500 ns
//Search execution time: 15500 ns

class HashMapSearchExample {
    public static void main(String[] args) {
        int size = 1_000_000;
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * 100); // 0~99 범위의 랜덤 값
        }

        // 해시맵 생성
        long creationStartTime = System.nanoTime();
        HashMap<Integer, List<Integer>> hashMap = createHashMap(array);
        long creationEndTime = System.nanoTime();

        // 타겟 값 설정
        int[] targets = {42, 50};

        // 탐색 시간 측정
        long searchStartTime = System.nanoTime();
        Map<Integer, List<Integer>> results = findIndicesForTargets(hashMap, targets);
        long searchEndTime = System.nanoTime();

        // 결과 출력
        printResultsWithValues(array, results);
        System.out.println("HashMap creation time: " + (creationEndTime - creationStartTime) + " ns");
        System.out.println("Search execution time: " + (searchEndTime - searchStartTime) + " ns");
    }

    public static HashMap<Integer, List<Integer>> createHashMap(int[] array) {
        HashMap<Integer, List<Integer>> hashMap = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            hashMap.computeIfAbsent(array[i], k -> new ArrayList<>()).add(i);
        }
        return hashMap;
    }

    public static Map<Integer, List<Integer>> findIndicesForTargets(HashMap<Integer, List<Integer>> hashMap, int[] targets) {
        Map<Integer, List<Integer>> results = new HashMap<>();
        for (int target : targets) {
            results.put(target, hashMap.getOrDefault(target, new ArrayList<>()));
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
