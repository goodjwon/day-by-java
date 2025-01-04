package com.example.ch3.que;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private final BlockingQueue<Integer> queue;
    private final int consumerId;

    public Consumer(BlockingQueue<Integer> queue, int consumerId) {
        this.queue = queue;
        this.consumerId = consumerId;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int value = queue.take();
                System.out.println("소비자 " + consumerId + "가 " + value + "를 소비했습니다.");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
