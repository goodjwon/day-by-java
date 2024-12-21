package com.example.ch3.day5;

import java.util.LinkedList;
import java.util.Queue;

public class Day5Example1 {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();

        queue.add("고객 1");
        queue.add("고객 2");
        queue.add("고객 3");

        System.out.println("Queue: " + queue);
    }
}
