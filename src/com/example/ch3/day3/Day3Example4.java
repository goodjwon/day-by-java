package com.example.ch3.day3;

import java.util.Vector;

public class Day3Example4 {
    public static void main(String[] args) {
        Vector<String> vector = new Vector<>();

        vector.add("One");
        vector.add("Two");
        vector.add("Three");

        vector.removeElementAt(1);
        System.out.println("Vector: " + vector);
    }
}
