package com.example.ch3.day2;

import java.util.LinkedList;

public class Day2Example3 {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        list.removeFirst();
        list.removeLast();

        System.out.println("LinkedList: " + list);
    }
}
