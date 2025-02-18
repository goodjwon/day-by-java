package com.example.ch4.functions.hw;

import java.util.function.Function;

public class PasswordMasking {
    public static void main(String[] args) {
        String passWord = "mypassword ";

        // 주민번호 등 식별번호 .. ******* 홍길동 => 홍*동

        Function<String, String> maskingFunction = pass -> "*".repeat(pass.length());

        Function<String, String> removeSpacesFunction =
                str -> str.trim();

        Function<String, String> finalFunction = removeSpacesFunction.andThen(maskingFunction);

        String result = finalFunction.apply(passWord);

        System.out.println(result);

//        List<String> password = Arrays.asList("mypassword");
//
//        Function<String, String> strFunction = s -> {
//            String result = String.valueOf(s);
//            System.out.print('"');
//            for (int i = 0; i < 10; i++) {
//                System.out.print("*");
//            }
//            System.out.println('"');
//
//            return result;
//        };
//
//        password.forEach(s -> strFunction.apply(s));
    }
}
