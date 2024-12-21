package com.example.ch3.day3;

import java.util.Vector;

public class Day3Example2 {
    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>(5);

        vector.add(1);
        vector.add(2);

        System.out.println("크기: " + vector.size());
        System.out.println("용량: " + vector.capacity());
    }
}
