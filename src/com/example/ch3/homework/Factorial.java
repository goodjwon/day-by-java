package com.example.ch3.homework;

public class Factorial {
    // 재귀적 팩토리얼 함수
    public static int factorial(int n) {
        System.out.println("factorial(" + n + ") 호출");
        if (n == 0) {
            System.out.println("factorial(0) = 1 (기본 사례)");
            return 1;
        } else {
            int result = n * factorial(n - 1);
            System.out.println("factorial(" + n + ") = " + result);
            return result;
        }
    }

    // 테스트
    public static void main(String[] args) {
        int num = 5;
        int result = factorial(num);
        System.out.println(num + "! = " + result);
    }
}
