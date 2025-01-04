package com.example.ch3.day5;

import java.util.ArrayDeque;

public class Day5Example9 {
    public static void main(String[] args) {
        ArrayDeque<String> deque = new ArrayDeque<>();

        deque.add("사과");
        deque.add("바나나");
        deque.add("체리");

        String first = deque.peekFirst();
        String last = deque.peekLast();

        System.out.println("첫 번째 요소: " + first);
        System.out.println("마지막 요소: " + last);
    }
}
