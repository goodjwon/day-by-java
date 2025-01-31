package com.example.ch4.json.hw;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class JsonDataSum {
    static class SaleData {
        String month;
        int sales;

        public SaleData(String month, int sales) {
            this.month = month;
            this.sales = sales;
        }

        @Override
        public String toString() {
            return "month: " + month + ", sales: " + sales;
        }
    }

    public static List<SaleData> readJson(String filename) {
        Gson gson = new Gson();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return gson.fromJson(sb.toString(), new TypeToken<List<SaleData>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void sumData(List<SaleData> saleData) {
        if (saleData != null) {
            int totalSales = 0;
            for (SaleData sale : saleData) {
                totalSales += sale.sales;
            }
            System.out.println("매출의 합: " + totalSales);
        } else {
            System.out.println("데이터가 없습니다.");
        }
    }

    public static String getResourceFilePath(String fileName) {
        ClassLoader classLoader = JsonDataSum.class.getClassLoader();
        if (classLoader.getResource(fileName) != null) {
            return classLoader.getResource(fileName).getPath();
        }
        return null;
    }

    public static void main(String[] args) {
        String inputFilePath = getResourceFilePath("sales.json");

        if (inputFilePath != null) {
            List<SaleData> saleData = readJson(inputFilePath);
            sumData(saleData);
        } else {
            System.out.println("파일을 찾을 수 없습니다.");
        }
    }
}
