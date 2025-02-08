package com.example.ch4.functions.test;

import java.util.function.Function;

public class FunctionAndThenExample {
    public static void main(String[] args) {
        // 1. Uppercase
        String a = "aaaa";
//        String A =a.toUpperCase();
//        System.out.println(a);
//        System.out.println(A);

        Function<String, String> toUpperCase = String::toUpperCase;

        // 2. s.length
//        System.out.println(a.length());
        Function<String, Integer> getLength = String::length;

        // *. andThen
        Function<String, Integer> combined = toUpperCase.andThen(getLength);

        // print => 4
        System.out.println(combined.apply(a));


    }
}
