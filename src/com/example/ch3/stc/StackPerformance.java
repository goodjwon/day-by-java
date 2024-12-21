package com.example.ch3.stc;

import java.util.Stack;

public class StackPerformance {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        int n = 1_000_000;

        //벡터 끝에 추가 기능
        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            stack.add(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("벡터 끝에 추가 시간: " + (end - start) + "ms");

        //중간 삽입 성능
        start = System.currentTimeMillis();
        for (int i = 0; i < n / 10; i++) {
            stack.add(stack.size() / 2, i);
        }
        end = System.currentTimeMillis();
        System.out.println("중간 삽입 시간: " + (end - start) + "ms");

        //데이터 제거 성능
        start = System.currentTimeMillis();
        while (!stack.empty()) {
            stack.pop();
        }
        end = System.currentTimeMillis();
        System.out.println("데이터 제거 시간: " + (end - start) + "ms");
    }
}
