package com.example.ch3.day4;

import java.util.Stack;

public class Day4Example3 {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();

        stack.push("A");
        stack.push("B");
        stack.push("C");

        String top = stack.peek();
        System.out.println("맨 위의 요소: " + top);
    }
}
