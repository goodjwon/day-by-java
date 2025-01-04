package com.example.ch3.day3;

import java.util.Vector;

public class Day3Example3 {
    public static void main(String[] args) {
        Vector<String> vector = new Vector<>();

        vector.add("첫 번째");
        vector.add("세 번째");

        vector.insertElementAt("두 번째",1);

        System.out.println("Vector: " + vector);
    }
}
