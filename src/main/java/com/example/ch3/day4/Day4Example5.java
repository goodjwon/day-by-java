package com.example.ch3.day4;

import java.util.Stack;

public class Day4Example5 {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();

        stack.push("Python");
        stack.push("Java");
        stack.push("C++");

        int position = stack.search("Java");
        System.out.println("Java의 위치: " + position);
    }
}
