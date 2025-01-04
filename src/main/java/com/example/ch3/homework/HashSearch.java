package com.example.ch3.homework;

import java.util.HashMap;

public class HashSearch {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();

        //데이터 삽입
        map.put("apple", "사과");
        map.put("banana", "바나나");
        map.put("cherry", "체리");

        //데이터 검색
        String key = "banana";
        if (map.containsKey(key)) {
            System.out.println(key + "의 값은 " + map.get(key) + "입니다.");
        } else {
            System.out.println(key + "를 찾을 수 없습니다.");
        }
    }
}
