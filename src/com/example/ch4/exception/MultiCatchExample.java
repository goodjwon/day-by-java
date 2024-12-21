package com.example.ch4.exception;

public class MultiCatchExample {
    public static void main(String[] args) {
        String[] values = {"100", null, "ABC"};

        for (String val : values) {
            try {
                int number = Integer.parseInt(val);
                //val이 null이면 NullPdintrException
                // 숫자로 변환 불가 시 NumberFormatException
                System.out.println("변환 성공: " + number);
            } catch (NullPointerException | NumberFormatException e) {
                System.out.println("변환 실패: " + e.getMessage());
            }
        }
    }
}
