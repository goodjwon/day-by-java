package com.example.ch4.functions.hw;

import java.util.function.Function;

public class UpperCase {
    public static void main(String[] args) {
        // char(11) =
        String baseWord = "hello world    ";  //15

        Function<String, String> upperCaseFunction =
                word -> word.toUpperCase();

        // 공백 제거를
        Function<String, String> removeSpacesFunction =
                str -> str.trim();

        Function<String, String> finalFunction =  removeSpacesFunction.andThen(upperCaseFunction);

        System.out.println('"' + finalFunction.apply(baseWord) + '"');

//        List<String> strings = Arrays.asList("hello world");
//
//        Function<String, String> strFunction = s -> {
//            String result = String.valueOf(s);
//            String upper = s.toUpperCase();
//            System.out.println('"' + upper + '"');
//
//            return result;
//        };
//
//        strings.forEach(s -> strFunction.apply(s));
    }
}
