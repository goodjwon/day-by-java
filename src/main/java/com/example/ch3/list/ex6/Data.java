package com.example.ch3.list.ex6;

import java.util.ArrayList;
import java.util.Random;

class DataAnalyzer {

    private ArrayList<Integer> dataList;

    public DataAnalyzer() {
        dataList = new ArrayList<>();
    }

    // 데이터 로드.
    public void loadData() {
        int loadData = 1_000_000;
        for(int i = 0; i < loadData; i++) {
            dataList.add(i);
        }
        System.out.println("데이터 로드 완료: " + loadData + "건");
    }

    // 데이터 처리.
    public void processData() {
        Random random = new Random();

        int total = 0;
        for (int i=0; i<10; i++){
            int index = random.nextInt(dataList.size());
            int data = dataList.get(index);
            System.out.println("인덱스 " + index + "의 데이터: " + data);
            total += data;
        }
        System.out.println("선택된 데이터의 합계: " + total);

    }
}

public class Data {
    public static void main(String[] args) {
        DataAnalyzer analyzer = new DataAnalyzer();
        analyzer.loadData();
        analyzer.processData();
    }
}
