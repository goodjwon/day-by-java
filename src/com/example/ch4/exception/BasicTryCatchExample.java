package com.example.ch4.exception;

public class BasicTryCatchExample {
    public static void main(String[] args) {

        try {
            int value = parseNumber("ABC");
            System.out.println("결과: " + value);
        } catch (NumberFormatException e) {
            System.err.println("잘못입력해서 변환할 수 없어요. 숫자만 입력해주세요. ");
        }

        System.out.println("정상 흐름 계속");
    }

    static int parseNumber(String input) {
        return Integer.parseInt(input); //숫자 변환 불가능 시 NumberFormatException 발생
    }
}

/*
코드 설명:
- parseNumber 메서드에서 "ABC"를 정수로 변환 시도, NumberFormatException 발생
- try 블록 내 예외 발생 시 catch 블록으로 제어 이동, 메시지 및 스택 트레이스 출력
- 예외 처리 후 프로그램은 정상 흐름 지속

Exception in thread "main" java.lang.NumberFormatException: For input string: "ABC"

*/
