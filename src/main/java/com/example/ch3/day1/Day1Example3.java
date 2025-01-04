package com.example.ch3.day1;

import java.util.ArrayList;

public class Day1Example3 {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);

        int num = numbers.get(1);
        System.out.println("인덱스 1의 값: " + num);

        numbers.set(1, 25);
        System.out.println("수정된 ArrayList: " + numbers);
    }
}
