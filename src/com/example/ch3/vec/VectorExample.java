package com.example.ch3.vec;

import java.util.Vector;

public class VectorExample {
    public static void main(String[] args) {
        Vector<String> vector = new Vector<>();

        // 데이터 추가
        vector.add("Apple");
        vector.add("Banana");
        vector.add("Cherry");

        //데이터 접근
        System.out.println("첫 번째 요소: " +vector.get(0));

        //데이터 수정
        vector.set(1, "Blueberry");
        System.out.println("수정된 벡터: " + vector);

        //데이터 삭제
        vector.remove(2);
        System.out.println("삭제 후 벡터: " + vector);

        //크기 확인
        System.out.println("벡터 크기: " + vector.size());
    }
}
