package com.example.ch3.list.ex5;

import java.util.LinkedList;
import java.util.Queue;

class MessageQueue {
    Queue<String> queue = new LinkedList<>();
    //private ArrayDeque<String> queue;

    public void sendMessage(String message) {
        queue.add(message);
    }

    public void receiveMessage() {
        queue.poll();
    }
}

public class Message {
    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue();
        messageQueue.sendMessage("안녕하세요");
        messageQueue.sendMessage("어떻게 지내세요?");

        System.out.println("수신된 메시지: " + messageQueue.queue.poll());
        System.out.println("수신된 메시지: " + messageQueue.queue.poll());
    }
}
