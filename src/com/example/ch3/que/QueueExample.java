package com.example.ch3.que;

import java.util.LinkedList;
import java.util.Queue;

public class QueueExample {
    public static void main(String[] args) {
        //큐 생성
        Queue<String> queue = new LinkedList<>();

        //요소 추가
        queue.offer("첫 번째");
        queue.offer("두 번째");
        queue.offer("세 번째");

        //큐 내용 출력
        System.out.println("현재 큐: " + queue);

        //요소 처리
        while (!queue.isEmpty()) {
            String item = queue.poll();
            System.out.println("처리된 요소: " + item);
            System.out.println("남은 큐: " + queue);
        }
    }
}
