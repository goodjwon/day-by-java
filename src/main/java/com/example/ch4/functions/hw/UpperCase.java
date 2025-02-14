package com.example.ch4.functions.hw;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class UpperCase {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("hello world");

        Function<String, String> strFunction = s -> {
            String result = String.valueOf(s);
            String upper = s.toUpperCase();
            System.out.println('"' + upper + '"');

            return result;
        };

        strings.forEach(s -> strFunction.apply(s));
    }
}
