package com.example.ch3.day5;

import java.util.LinkedList;
import java.util.Queue;

public class Day5Example5 {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();

        queue.add("A");
        queue.add("B");
        queue.add("C");

        queue.clear();
        System.out.println("Queue: " + queue);
    }
}
