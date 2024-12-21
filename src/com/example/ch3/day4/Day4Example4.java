package com.example.ch3.day4;

import java.util.Stack;

public class Day4Example4 {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();

        System.out.println("Stack이 비었나요? " + stack.isEmpty());

        stack.push("데이터");
        System.out.println("Stack이 비었나요? " + stack.isEmpty());
    }
}
