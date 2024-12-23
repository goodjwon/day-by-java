package com.example.ch3.homework;

public class BinarySearch {
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        //반복문을 통한 이진 검색
        while (left <= right) {
            int mid = left + (right - left) / 2;
            System.out.println("중간 인덱스: " + mid + ", 값: " + arr[mid]);

            //목표 값과 중간 값 비교
            if (arr[mid] == target) {
                return mid; //인덱스 반환
            }

            if (arr[mid] < target) {
                left = mid + 1; //오른쪽 부분 검색
                System.out.println("오른쪽 부분 검색: [" + left + ", " + right + "]");
            } else {
                right = mid - 1; //왼쪽 부분 검색
                System.out.println("왼쪽 부분 검색: [" + left + ", " + right + "]");
            }
        }

        return -1; //찾지 못한 경우
    }

    //테스트
    public static void main(String[] args) {
        int[] data = { 2, 3, 5, 7, 11, 13, 17 };
        int target = 11;
        System.out.println("배열: " + java.util.Arrays.toString(data));
        int result = binarySearch(data, target);

        if (result == -1) {
            System.out.println("원소를 찾을 수 없습니다.");
        } else {
            System.out.println("원소가 인덱스 " + result + "에 있습니다.");
        }
    }
}
