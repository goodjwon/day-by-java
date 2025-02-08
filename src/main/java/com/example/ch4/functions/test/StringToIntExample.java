package com.example.ch4.functions.test;


import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class StringToIntExample {
    public static void main(String[] args) {

        List<String> numbers = Arrays.asList("123", "456", "789");

        Function<String, Integer> intFunction = s -> {
            Integer result = Integer.parseInt(s);
            System.out.println("Converted: " + result + " (Type: " + result.getClass().getSimpleName() + ")");

            return result;
        } ;

        Function<String, Integer> intFunctions = Integer::parseInt;

        numbers.forEach(s -> System.out.println(Integer.parseInt(s)));

        numbers.forEach(s -> intFunction.apply(s));

        // "123", "456", "789"
        // 1. Arrays.asList 선언
        // 2. for 이치
        // 3. 형변환 처리.
    }
}
