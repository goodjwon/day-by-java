package com.example.ch3.alog;

import java.util.*;

/**
 Array creation time: 68745167 ns
 Array sorting time: 30957667 ns
 Search execution time: 899542 ns
 */


//Array creation time: 25898000 ns
//Array sorting time: 124066800 ns
//Search execution time: 1592800 ns


class BinarySearchExample {
    public static void main(String[] args) {
        int size = 1_000_000;

        //배열 생성 시간 측정
        long creationStartTime = System.nanoTime();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * 100); // 0~99 범위의 랜덤 값
        }
        long creationEndTime = System.nanoTime();

        //배열 정렬 시간 측정
        long sortStartTime = System.nanoTime();
        Arrays.sort(array);
        long sortEndTime = System.nanoTime();

        int[] targets = {42, 50}; // 여러 타겟 값

        //탐색 시간 측정
        long searchStartTime = System.nanoTime();
        Map<Integer, List<Integer>> results = findIndicesForTargets(array,targets);
        long searchEndTime = System.nanoTime();

        //결과 출력
        printResultsWithValues(array, results);

        //시간 출력
        System.out.println("Array creation time: " + (creationEndTime - creationStartTime) + " ns");
        System.out.println("Array sorting time: " + (sortEndTime - sortStartTime) + " ns");
        System.out.println("Search execution time: "+ (searchEndTime - searchStartTime) + " ns");

    }

    public static Map<Integer, List<Integer>> findIndicesForTargets(int[] array, int[] targets) {
        Map<Integer, List<Integer>> results = new HashMap<>();
        for (int target : targets) {
            results.put(target, findAllIndices(array, target));
        }
        return results;
    }

    public static List<Integer> findAllIndices(int[] array, int target) {
        List<Integer> indices = new ArrayList<>();
        int firstIndex = findFirstIndex(array, target);
        if (firstIndex != -1) {
            for (int i = firstIndex; i < array.length && array[i] == target; i++) {
                indices.add(i);
            }
        }
        return indices;
    }

    public static int findFirstIndex(int[] array, int target) {
        int low = 0, high = array.length - 1, result = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (array[mid] == target) {
                result = mid;
                high = mid - 1;
            } else if (array[mid] < target) {
                low = mid +1;
            } else {
                high = mid -1;
            }
        }
        return result;
    }

    public static void printResultsWithValues(int[] array, Map<Integer, List<Integer>> results) {
        for (Map.Entry<Integer, List<Integer>> entry : results.entrySet()) {
            int target = entry.getKey();
            List<Integer> indices = entry.getValue();
            if (indices.isEmpty()) {
                System.out.println("Target " + target + "not found.");
            } else {
                System.out.println("Target " + target + "found at indices: ");
                for (int index : indices) {
                    System.out.println("Index: " + index + ", Value: " + array[index]);
                }
            }
        }
    }
}
