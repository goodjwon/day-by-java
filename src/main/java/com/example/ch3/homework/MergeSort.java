package com.example.ch3.homework;

public class MergeSort {
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            //중간 인덱스 계산
            int mid = (left + right) / 2;
            System.out.println("분할: [" + left + ", " + mid + "] 와 ]" + (mid + 1) + ", " + right + "]");

            //좌우 분할 정렬
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            //병합
            merge(arr, left, mid, right);
            System.out.println("병합 결과: " + java.util.Arrays.toString(arr));
        }
    }

    //병합 함수
    private static void merge(int[] arr, int left, int mid, int right) {
        //두 부분 배열의 크기
        int n1 = mid - left + 1;
        int n2 = right - mid;

        //임시 배열 생성
        int[] L = new int[n1];
        int[] R = new int[n2];

        //데이터 복사
        System.arraycopy(arr, left, L, 0, n1);
        System.arraycopy(arr, mid + 1, R, 0, n2);

        //병합 과정
        int i = 0, j = 0;
        int k = left;
        System.out.println("병합 중: L[" + java.util.Arrays.toString(L) + ", R" + java.util.Arrays.toString(R));
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) {
            arr[k++] = L[i++];
        }
        while (j < n2) {
            arr[k++] = R[j++];
        }
    }

    //테스트
    public static void main(String[] args) {
        int[] data = { 12, 11, 13, 5, 6, 7 };
        System.out.println("정렬 전 배열: " + java.util.Arrays.toString(data));
        mergeSort(data, 0, data.length - 1);
        System.out.println("정렬 후 배열: " + java.util.Arrays.toString(data));
    }
}
