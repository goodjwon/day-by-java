package com.example.ch3.day3;

import java.util.Vector;

public class Day3Example5 {
    public static void main(String[] args) {
        Vector<String> vector = new Vector<>();

        vector.add("Red");
        vector.add("Green");
        vector.add("Blue");

        int index = vector.indexOf("Green");
        System.out.println("Green의 인덱스: " + index);
    }
}
