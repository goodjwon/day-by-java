package com.example.ch4.exception;

class FileProcessingException extends Exception {
    FileProcessingException(String msg) {
        super(msg);
    }
}

public class CustomFileExceptionExample {
    public static void main(String[] args) {
        try {
            processFile("non_exist_file.txt");
        } catch (FileProcessingException e) {
            System.out.println("커스텀 예외 발생: " + e.getMessage());
        }
    }

    static void processFile(String filename) throws FileProcessingException {
        //파일이 존재하지 않거나 권한 문제 시 커스텀 예외 발생
        if (!new java.io.File(filename).exists()) {
            throw new FileProcessingException("파일 처리 불가 - 파일이 존재하지 않음: " + filename);
        }
        //실재 파일 처리 로직 가정 가능
    }
}

/*
코드 설명:
- FileProcessingException 이라는 커스텀 예외 정의
- processFile 메서드에서 파일 존재 여부 체크 후 없을 경우 커스텀 예외 발생
- 호출 측에서 해당 예외를 받아 처리, 구체적인 상황(파일 미존재) 명확히 전달
*/
