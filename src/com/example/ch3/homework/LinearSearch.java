package com.example.ch3.homework;

public class LinearSearch {
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println("인덱스 " + i + "에서 검사: 값 " + arr[i]);
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    //테스트
    public static void main(String[] args) {
        int[] data = {5, 3, 8, 4, 2};
        int target = 4;
        System.out.println("배열: " + java.util.Arrays.toString(data));
        int result = linearSearch(data, target);

        if (result == -1) {
            System.out.println("원소를 찾을 수 없습니다.");
        } else {
            System.out.println("원소가 인덱스 " + result + "에 있습니다.");
        }
    }
}
