package com.example.ch4.functions;


import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Name {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        /**
        Name: Alice
        Length: 5
        Name: Bob
        Length: 3
        Name: Charlie
        Length: 7
         **/
        System.out.println("============for문 함수.============");
        names.forEach(s->{
            System.out.println("Name: " + s);
            System.out.println("Length: " + s.length());
        });

        // 인덱스 기반 for 루프
        System.out.println("============ 인덱스 기반 for 루프===========");
        for(int i=0; i<names.size(); i++) {
            String name = names.get(i);
            System.out.println("Name: " + name);
            System.out.println("Length: " + name.length());
        }


        // 함수형
        System.out.println("============함수형==========");
        Consumer<String> printName = name ->  System.out.println("Name: " + name);
        Consumer<String> printLength = name -> System.out.println("Length: " + name.length());

        Consumer<String> combined = printName.andThen(printLength);

        names.forEach(combined);





    }
}
