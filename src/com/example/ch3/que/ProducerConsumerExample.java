package com.example.ch3.que;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerExample {
    public static void main(String[] args) {
        //버퍼 크기가 5인 큐 생성
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

        //생산자 및 소비자 생성
        Producer producer1 = new Producer(queue, 1);
        Producer producer2 = new Producer(queue, 2);
        Consumer consumer1 = new Consumer(queue, 1);
        Consumer consumer2 = new Consumer(queue, 2);

        //스레드 시작
        new Thread(producer1).start();
        new Thread(producer2).start();
        new Thread(consumer1).start();
        new Thread(consumer2).start();
    }
}
