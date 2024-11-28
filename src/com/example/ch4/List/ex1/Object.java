package com.example.ch4.List.ex1;

import java.util.ArrayList;

public class Object {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("노트북 - 1500000원");
        list.add("스마트폰 - 800000원");
        list.add("태블릿 - 600000원");

        for (String object : list) {
            System.out.println(object);
        }

        System.out.println("두 번쨰 상품: " + list.get(1));

        for (int i = 1; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
