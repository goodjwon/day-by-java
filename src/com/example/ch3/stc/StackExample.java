package com.example.ch3.stc;

import java.util.Stack;

public class StackExample {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();

        //데이터 추가
        stack.push("Apple");
        stack.push("Banana");
        stack.push("Cherry");

        //데이터 확인
        System.out.println("스택의 맨 위 데이터: " + stack.peek());
        System.out.println("스택의 맨 위 데이터: " + stack.peek());
        System.out.println("스택의 맨 위 데이터: " + stack.peek());

        //데이터 제거
        System.out.println("제거된 데이터: " + stack.pop());

        System.out.println("스택의 맨 위 데이터: " + stack.peek());

        //스택이 비었는지 확인
        System.out.println("스택이 비었나요? " + stack.empty());
    }
}
