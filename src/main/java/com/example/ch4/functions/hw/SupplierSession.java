package com.example.ch4.functions.hw;


// session => 사용자정보 (세션 고유id, 이름, id(seq), 로그인 시간); => 서버
// 세션 고유id => 사용자 (cookie), 시간리셋. 30분, 30분, 30분, ... 자동 로그아웃.

import java.util.function.Supplier;

class UserSession {
    private String userId;
    private long expiryTime;

    public UserSession(String userId, long expiryTime) {
        this.userId = userId;
        this.expiryTime = expiryTime;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "userId=" + userId +
                ", expiryTime=" + expiryTime +
                '}';
    }
}

/**
 * userId = user1234
 * login 유효시간은  360000 => 30분,
 */
public class SupplierSession {
    public static void main(String[] args) {
        Supplier<UserSession> sessionSupplier = () ->
            {
                return new UserSession(
                        "user1234",
                        System.currentTimeMillis()+360000);
            };

        System.currentTimeMillis();

        System.out.println(sessionSupplier.get());
    }
}
