package com.example.ch3.day1;

import java.util.ArrayList;

public class Day1Example4 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("고양이");
        list.add("강아지");
        list.add("토끼");

        list.remove("강아지");
        System.out.println("ArrayList: " + list);
    }
}
