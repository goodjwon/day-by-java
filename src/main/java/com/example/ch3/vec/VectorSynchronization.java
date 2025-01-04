package com.example.ch3.vec;

import java.util.ArrayList;

public class VectorSynchronization {
    public static void main(String[] args) {
        ArrayList<Integer> vector = new ArrayList();

        //여러 스레드에서 데이터 추가
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                vector.add(i);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 5; i < 10; i++) {
                vector.add(i);
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("벡터 데이터: " + vector);
    }
}
