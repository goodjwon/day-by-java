package com.example.ch3.stc;

import java.util.Stack;

public class StackIteration {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        //데이터 추가
        for (int i = 0; i <= 5; i++) {
            stack.push(i);
        }

        // 1. for-each 루프
        for (int item : stack) {
            System.out.println(item);
        }

        //2. pop을 이용해 데어터 제거하면서 출력
        while (!stack.empty()) {
            System.out.println("제거된 데이터: " + stack.pop());
        }
    }
}
