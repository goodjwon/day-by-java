package com.example.ch3.day1;

import java.util.ArrayList;
import java.util.Collections;

public class Day1Example5 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("오렌지");
        list.add("바나나");
        list.add("사과");

        Collections.sort(list);
        System.out.println("정렬된 ArrayList: " + list);
    }
}
