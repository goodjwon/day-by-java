package com.example.ch3.homework;

public class SelectionSort {
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                System.out.println("비교: " + arr[minIndex] + "와 " + arr[j]);
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
            System.out.println("교환 후 배열: " + java.util.Arrays.toString(arr));
        }
    }

    //테스트
    public static void main(String[] args) {
        int[] data = {64, 25, 12, 22, 11};
        System.out.println("정렬 전 배열: " + java.util.Arrays.toString(data));
        selectionSort(data);
        System.out.println("정렬 후 배열: " + java.util.Arrays.toString(data));
    }
}
