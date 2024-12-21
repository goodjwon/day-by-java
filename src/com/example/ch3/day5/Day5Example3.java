package com.example.ch3.day5;

import java.util.LinkedList;
import java.util.Queue;

public class Day5Example3 {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();

        queue.add("첫 번째");
        queue.add("두 번째");
        queue.add("세 번째");

        String element = queue.peek();
        System.out.println("첫 번째 요소: " + element);
    }
}
