package com.example.ch3.homework;

public class QuickSort {
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            //분할 인덱스
            int pi = partition(arr, low, high);
            System.out.println("피벗 위치: " + pi + "배열 상태: " + java.util.Arrays.toString(arr));

            //분할된 부분 정렬
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    //분할 함수
    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];//피벗 선택
        System.out.println("피벗 선택: " + pivot);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            System.out.println("비교: " + arr[j] + "와 피벗 " + pivot);
            if (arr[j] <= pivot) {
                i++;
                //교환
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                System.out.println("교환 발생: " + java.util.Arrays.toString(arr));
            }
        }

        //피벗을 올바른 위치에 교환
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        System.out.println("피벗 교환 후 배열: " + java.util.Arrays.toString(arr));

        return i + 1;
    }

    //테스트
    public static void main(String[] args) {
        int[] data = {10, 7, 8, 9, 1, 5};
        System.out.println("정렬 전 배열: " + java.util.Arrays.toString(data));
        quickSort(data, 0, data.length - 1);
        System.out.println("정렬 후 배열: " + java.util.Arrays.toString(data));
    }
}
