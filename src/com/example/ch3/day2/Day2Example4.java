package com.example.ch3.day2;

import java.util.LinkedList;

public class Day2Example4 {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        list.add("사과");
        list.add("바나나");
        list.add("체리");

        boolean containsApple = list.contains("사과");
        System.out.println("사과가 있나요? " + containsApple);
    }
}
