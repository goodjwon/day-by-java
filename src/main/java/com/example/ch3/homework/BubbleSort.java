package com.example.ch3.homework;

public class BubbleSort {
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;

        //배열 전체에 대해 반복
        for (int i = 0; i < n -1; i++) {
            swapped = false;
            //인접 요소 비교 및 교환
            for (int j = 0; j < n - i - 1; j++) {
                System.out.println("비교: " + arr[j] + "와 " + arr[j + 1]);
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                    System.out.println("교환 발생: " + java.util.Arrays.toString(arr));
                }
            }
            if (!swapped) break;
        }
    }

    //테스트
    public static void main(String[] args) {
        int[] data = { 5, 1, 4, 2, 8 };
        System.out.println("정렬 전 배열: " + java.util.Arrays.toString(data));
        bubbleSort(data);
        System.out.println("정렬 후 배열: " + java.util.Arrays.toString(data));
    }
}
