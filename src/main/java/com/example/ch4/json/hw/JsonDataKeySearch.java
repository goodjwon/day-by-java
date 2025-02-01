package com.example.ch4.json.hw;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.List;

public class JsonDataKeySearch {
    static class ProductsData {
        int id;
        String name;
        double price;

        public ProductsData(int id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        @Override
        public String toString() {
            return "id: " + id + ", name: " + name + ", price: " + price;
        }
    }

    public static List<ProductsData> readJson(String filename) {
        Gson gson = new Gson();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return gson.fromJson(sb.toString(), new TypeToken<List<ProductsData>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void printPrice(List<ProductsData> productsData) {
        if (productsData != null) {
            for (ProductsData product : productsData) {
                System.out.println("제품 " + product.name + "의 가격: " + product.price);
            }
        } else {
            System.out.println("데이터가 없습니다.");
        }
    }

    public static String getResourceFilePath(String fileName) {
        ClassLoader classLoader = JsonDataKeySearch.class.getClassLoader();
        if (classLoader.getResource(fileName) != null) {
            return classLoader.getResource(fileName).getPath();
        }
        return null;
    }

    public static void main(String[] args) {
        String inputFilePath = getResourceFilePath("products.json");

        if (inputFilePath != null) {
            List<ProductsData> productsData = readJson(inputFilePath);
            printPrice(productsData);
        } else {
            System.out.println("파일을 찾지 못했습니다");
        }
    }
}
