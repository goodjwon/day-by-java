package com.example.ch3.homework.alog;

import java.util.HashSet;

public class DiscountCodeValidator {
    public static void main(String[] args) {
        String discountCode = "SAVE2023";
        if (isValid(discountCode)) {
            System.out.println("유효");
        } else {
            System.out.println("무효");
        }
    }

    public static boolean isValid(String discountCode) {
        HashSet<Character> seenCharacters = new HashSet<>();

        for (char c : discountCode.toCharArray()) {
            if (!seenCharacters.add(c)) {
                return false;
            }
        }

        return true;
    }
}
