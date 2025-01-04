package com.example.ch3.def;

import java.util.ArrayList;

public class ArrayListInsertionDeletion {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("One");
        list.add("Three");

        list.add(1, "Two");
        System.out.println("중간 삽입 후: " + list);

        list.remove(1);
        System.out.println("중간 삭제 후: " + list);
    }
}
