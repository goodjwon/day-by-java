package com.example.ch4.List.ex6;

class DataAnalyzer {

    public void loadData() {
        int loadData = 1000000;
        System.out.println("데이터 로드 완료: " + loadData + "건");
    }

    public void processData() {}
}

public class Data {
    public static void main(String[] args) {
        DataAnalyzer analyzer = new DataAnalyzer();
        analyzer.loadData();
    }
}
