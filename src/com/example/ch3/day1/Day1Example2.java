package com.example.ch3.day1;

import java.util.ArrayList;

public class Day1Example2 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("첫 번째");
        list.add("세 번째");

        list.add(1, "두 번째");

        System.out.println("ArrayList: " + list);
    }
}
