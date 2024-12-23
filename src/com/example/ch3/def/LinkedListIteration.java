package com.example.ch3.def;

import java.util.LinkedList;

public class LinkedListIteration {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        list.add("One");
        list.add("Two");
        list.add("Three");

        for (String item : list) {
            System.out.println(item);
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println("인덱스 " + i + ": " + list.get(i));
        }

        list.iterator().forEachRemaining(System.out::println);
    }
}
