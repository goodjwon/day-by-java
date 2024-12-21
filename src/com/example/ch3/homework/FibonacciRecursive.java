package com.example.ch3.homework;

public class FibonacciRecursive {
    public static int fib(int n) {
        if (n <= 1) {
            return n; // 기본 사례
        } else {
            return fib(n - 1) + fib(n - 2); // 재귀 사례
        }
    }

    // 테스트
    public static void main(String[] args) {
        int n = 40;
        System.out.println("F(" + n + ") = " + fib(n)); // 계산 시간이 매우 오래 걸림
    }
}
