package com.example.ch3.def;

import java.util.HashSet;
import java.util.Set;

public class SetExample {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(2);
        set.add(3);

        for (int number : set) {
            System.out.println(number);
        }
    }
}
