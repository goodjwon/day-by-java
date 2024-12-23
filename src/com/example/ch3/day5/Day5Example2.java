package com.example.ch3.day5;

import java.util.LinkedList;
import java.util.Queue;

public class Day5Example2 {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(100);
        queue.add(200);
        queue.add(300);

        int num = queue.poll();
        System.out.println("제거된 요소: " + num);
        System.out.println("현재 Queue: " + queue);
    }
}
