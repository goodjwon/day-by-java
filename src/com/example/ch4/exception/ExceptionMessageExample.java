package com.example.ch4.exception;

public class ExceptionMessageExample {
    public static void main(String[] args) {
        try {
            riskyOperation();
        } catch (RuntimeException e) {
            System.err.println("예외 메시지: " + e.getMessage());
            System.err.println("스택 추적 정보: ");
            e.printStackTrace();
        }
    }

    static  void riskyOperation()  {
        throw new RuntimeException("위험한 상황 발생");
    }

    static String findMember(){
        String memberid = memberService("0001");
//        if(memberid.equals("00001")){
//            throw new MemberNotFoundException("회원을 찾을 수 없습니다.");
//        }
        return memberid;
    }

    static String memberService(String memberId){
        return "00001";
    }
}
