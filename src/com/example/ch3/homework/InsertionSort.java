package com.example.ch3.homework;

public class InsertionSort {
    public static void insertionSort(int[] arr) {
        int n = arr.length;

        //두 번째 요소부터 시작
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            System.out.println("키 값: " + key);

            //키 값보다 큰 요소를 오른쪽으로 이동
            while (j >= 0 && arr[j] > key) {
                System.out.println("이동: " + arr[j]);
                arr[j + 1] = arr[j];
                j--;
            }

            //키 값을 올바른 위치에 삽입
            arr[j + 1] = key;
            System.out.println("삽입 후 배열: " + java.util.Arrays.toString(arr));
        }
    }

    //테스트
    public static void main(String[] args) {
        int[] data = {12, 11, 13, 5, 6};
        System.out.println("정렬 전 배열: " + java.util.Arrays.toString(data));
        insertionSort(data);
        System.out.println("정렬 후 배열: " + java.util.Arrays.toString(data));
    }
}
