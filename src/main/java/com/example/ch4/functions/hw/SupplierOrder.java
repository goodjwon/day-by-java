package com.example.ch4.functions.hw;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.function.Supplier;

public class SupplierOrder {
    public static void main(String[] args) {

        Supplier<String> order = () -> {
            String dateTime
                    = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

            Random random = new Random();
            int randomNum = random.nextInt(9000) + 1000;

            /**
             * data 시퀀스. (*)
             * 시퀀스 구현. 싱크로나이즈드. => lock
             */

            return "ORDER-" + dateTime + randomNum;
        };
        String orderNumber = order.get();
        System.out.println('"' + orderNumber + '"');
    }
}

