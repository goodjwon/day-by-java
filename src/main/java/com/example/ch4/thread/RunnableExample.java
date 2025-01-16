package com.example.ch4.thread;

class MyRunnable implements Runnable {
    public void run() {
        System.out.println("Runnable 실행 중");
    }
}

public class RunnableExample {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable());
        thread.start();
    }
}
