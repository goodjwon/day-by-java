package com.example.ch4.exception;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TryCatchFinallyExample {
    public static void main(String[] args) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("data.txt"));
            String line = reader.readLine();
            System.out.println("읽은 내용: " + line);
        } catch (FileNotFoundException e){
            System.out.println("파일을 찾을 수 없음: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("파일 읽기 중 예외: " + e.getMessage());
        } finally {
            //자원 정리 로직
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("자원 정리 중 예외: " + e.getMessage());
                }
            }
        }
    }
}

/*
코드 설명:
- 파일 읽기 시 IOException 발생 가능
- try-catch로 예외 처리 후 finally 블록에서 BufferedReader 자원 해제 보장
- 예외 발생 여부와 관계없이 finally 블록 실행
*/
