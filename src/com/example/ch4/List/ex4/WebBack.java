package com.example.ch4.List.ex4;

import java.util.Stack;

class BrowserHistory {

    Stack<String> webPage = new Stack<>();

    public void visit(String page) {
        webPage.push(page);

        System.out.println("방문: " + page);
    }

    public void goBack() {
        System.out.println("뒤로가기: " + webPage.pop());
        System.out.println("현재 페이지: " + webPage.peek());
    }
}

public class WebBack {
    public static void main(String[] args) {
        BrowserHistory browser  = new BrowserHistory();
        browser.visit("홈");
        browser.visit("소개");
        browser.visit("문의");
        browser.goBack();
        browser.goBack();
    }
}
