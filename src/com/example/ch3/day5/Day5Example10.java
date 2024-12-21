package com.example.ch3.day5;

import java.util.ArrayDeque;

public class Day5Example10 {
    public static void main(String[] args) {
        ArrayDeque<String> deque = new ArrayDeque<>();

        deque.add("데이터 1");
        deque.add("데이터 2");
        deque.add("데이터 3");

        for (String data : deque) {
            System.out.println(data);
        }
    }
}
