package com.example.ch3.homework;

public class FibonacciDP {
    public static int fib(int n) {
        int[] dp = new int[n + 1];

        // 기본 사례 초기화
        dp[0] = 0;
        if (n > 0) dp[1] = 1;

        // 바텀업 방식으로 피보나치 수열 계산
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    // 테스트
    public static void main(String[] args) {
        int n = 40;
        System.out.println("F(" + n + ") = " + fib(n)); // 빠르게 계산 가능
    }
}
