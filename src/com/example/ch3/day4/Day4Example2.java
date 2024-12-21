package com.example.ch3.day4;

import java.util.Stack;

public class Day4Example2 {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        stack.push(10);
        stack.push(20);
        stack.push(30);

        int num = stack.pop();
        System.out.println("제거된 요소: " + num);
        System.out.println("현재 stack: " + stack);
    }
}
