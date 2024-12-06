package com.example.ch4.List.ex4;

import java.util.Stack;

class BrowserHistory {

    private final String page1;
    private final String page2;
    private final String page3;

    public BrowserHistory(String page1, String page2, String page3) {
        this.page1 = page1;
        this.page2 = page2;
        this.page3 = page3;
    }

    public void goBack() {
    }

    public void visit() {
        Stack<String> webPage = new Stack<>();
        webPage.push(page1);
        webPage.push(page2);
        webPage.push(page3);

        for (String site : webPage) {
            System.out.println("방문: " + site);
        }
    }
}

public class WebBack {
    public static void main(String[] args) {
        BrowserHistory browser  = new BrowserHistory("홈", "소개", "문의");
        browser.visit();
        browser.goBack();
    }
}
