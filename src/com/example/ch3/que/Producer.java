package com.example.ch3.que;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private final BlockingQueue<Integer> queue;
    private final int producerId;

    public Producer(BlockingQueue<Integer> queue, int producerId) {
        this.queue = queue;
        this.producerId = producerId;
    }

    @Override
    public void run() {
        try {
            int value = 0;
            while (true) {
                System.out.println("생산자 " + producerId + "가 " + value + "를 생산했습니다.");
                queue.put(value++);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
