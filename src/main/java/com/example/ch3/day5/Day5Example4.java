package com.example.ch3.day5;

import java.util.LinkedList;
import java.util.Queue;

public class Day5Example4 {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();

        System.out.println("Queue가 비었나요? " + queue.isEmpty());

        queue.add("데이터");
        System.out.println("Queue가 비었나요? " + queue.isEmpty());
    }
}
