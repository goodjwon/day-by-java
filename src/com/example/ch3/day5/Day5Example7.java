package com.example.ch3.day5;

import java.util.ArrayDeque;

public class Day5Example7 {
    public static void main(String[] args) {
        ArrayDeque<String> deque = new ArrayDeque<>();

        deque.addFirst("첫 번째");
        deque.addLast("마지막");

        System.out.println("ArrayDeque: " + deque);
    }
}
