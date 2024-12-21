package com.example.ch3.day5;

import java.util.ArrayDeque;

public class Day5Example8 {
    public static void main(String[] args) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        deque.add(10);
        deque.add(20);
        deque.add(30);

        int numFirst = deque.pollFirst();
        int numLast = deque.pollLast();

        System.out.println("제거도니 첫 번째 요소: " + numFirst);
        System.out.println("제거된 마지막 요소: " + numLast);
        System.out.println("ArrayDeque: " + deque);
    }
}
