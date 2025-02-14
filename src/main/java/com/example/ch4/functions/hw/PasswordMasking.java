package com.example.ch4.functions.hw;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class PasswordMasking {
    public static void main(String[] args) {
        List<String> password = Arrays.asList("mypassword");

        Function<String, String> strFunction = s -> {
            String result = String.valueOf(s);
            System.out.print('"');
            for (int i = 0; i < 10; i++) {
                System.out.print("*");
            }
            System.out.println('"');

            return result;
        };

        password.forEach(s -> strFunction.apply(s));
    }
}
