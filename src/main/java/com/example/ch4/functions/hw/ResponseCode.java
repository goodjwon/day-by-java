package com.example.ch4.functions.hw;

import com.example.ch2.game.ex9.Inventory;

import java.util.function.Function;

public class ResponseCode {
    //http 상태코드. 400 => 사용자 요청오류,
    // 404 => NOT_FOUND,
    // 500 => 서버 오류 SERVER_ERROR,
    // 200 => 성공, SUCCESS
    //httpClient -- apache
    public static void main(String[] args) {
        int responseCode = 404;

        Function<Integer, String> responseFunction = code -> {
            if (code >= 200 && code < 300) return "SUCCESS";
            else if (code == 404) return "NOT_FOUND";
            else if (code >= 500 && code < 600) return "SERVER_ERROR";
            else return "UNKNOWN";
        };

        String result =  responseFunction.apply(responseCode);

        System.out.println(result);
    }
}
