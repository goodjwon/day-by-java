package com.example.ch4.hw;

import java.io.Serializable;

public class Person implements Serializable {
    private String name;
    private int age;

    //직렬화를 위한 고정 버전 UID (선택사항)
    private static final long serialVersionUID = 1L;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    //기본 생성자 (역직렬화 시 필요할 수 있음)
    public Person() {
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    //CSV 등에서 사용할 간단한 문자열 변환
    public String toDataString() {
        return name + "," + age;
    }

    //CSV에서 문자열 읽어와 Person 생성
    public static Person fromDataString(String data) {
        String[] tokens = data.split(",");
        String name = tokens[0];
        int age = Integer.parseInt(tokens[1]);
        return new Person(name, age);
    }
}
