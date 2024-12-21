package com.example.ch3.list.ex4;

import java.util.Stack;

public class BrowserHistory {

    Stack<String> webPage = new Stack<>();

    // ArrayDeque<String> historyStack;

    public void visit(String page) {
        webPage.push(page);

        System.out.println("방문: " + page);
    }

    public void goBack() {
        System.out.println("뒤로가기: " + webPage.pop());
        System.out.println("현재 페이지: " + webPage.peek());
    }


    public static void main(String[] args) {
        BrowserHistory browser = new BrowserHistory();
        browser.visit("홈");
        browser.visit("소개");
        browser.visit("문의");
        browser.goBack();
        browser.goBack();
    }
}
