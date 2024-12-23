package com.example.ch3.def;

import java.util.ArrayList;

public class ArrayListPerformance {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        int n = 1_000_000;

        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("배열 끝에 추가 시간: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = 0; i < n / 10; i++) {
            list.add(list.size() / 2, i);
        }
        end = System.currentTimeMillis();
        System.out.println("중간 삽입 시간: " + (end - start) + "ms");
    }
}
