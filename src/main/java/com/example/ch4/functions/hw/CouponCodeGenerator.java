package com.example.ch4.functions.hw;

import java.util.Random;
import java.util.function.Supplier;

public class CouponCodeGenerator {
    public static void main(String[] args) {

        Supplier<String> couponCode = () -> {
            Random random = new Random();
            StringBuilder coupon = new StringBuilder();

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 4; j++) {
                    if (random.nextBoolean()) {
                        char randomChar = (char) ('A' + random.nextInt(26));
                        coupon.append(randomChar);
                    } else {
                        int randomNum = random.nextInt(10);
                        coupon.append(randomNum);
                    }
                }
                if (i < 2) {
                    coupon.append("-");
                }
            }
            return coupon.toString();
        };

        String couponCodeStr = couponCode.get();
        System.out.println('"' + couponCodeStr + '"');
    }
}
