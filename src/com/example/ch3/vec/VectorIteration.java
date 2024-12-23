package com.example.ch3.vec;

import java.util.Vector;

public class VectorIteration {
    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();

        for (int i = 0; i <= 5; i++) {
            vector.add(i);
        }

        //1. for-each 루프
        for (int num : vector) {
            System.out.println(num);
        }

        //2. for 루프
        for (int i = 0; i < vector.size(); i++) {
            System.out.println("인덱스 " + i + ": " + vector.get(i));
        }

        //3. 람다 표현식 (Java 8 이상)
        vector.forEach(num -> System.out.println("람다: " + num));
    }
}
