package com.example.ch3.def;

import java.util.ArrayList;

public class ArrayListIteration {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();

        for (int i = 0; i <= 5; i++) {
            numbers.add(i);
        }

        for (int num : numbers) {
            System.out.println(num);
        }

        for (int i = 0; i < numbers.size(); i++) {
            System.out.println("인덱스 " + i + ": " + numbers.get(i));
        }

        numbers.forEach(num -> System.out.println("람다: " + num));
    }
}
