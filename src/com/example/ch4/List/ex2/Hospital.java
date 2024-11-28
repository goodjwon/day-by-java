package com.example.ch4.List.ex2;

import java.util.LinkedList;
import java.util.List;

public class Hospital {
    public static void main(String[] args) {
        List<String> patient = new LinkedList<>();
        patient.add("홍길동");
        patient.add("이순신");
        patient.add(1,"긴급환자");

        for (String number : patient) {
            System.out.println("환자: " + number);
        }
        System.out.println("진료 중: 환자: " + patient.remove(0));

        for (String number : patient) {
            System.out.println("환자: " + number);
        }
    }
}
