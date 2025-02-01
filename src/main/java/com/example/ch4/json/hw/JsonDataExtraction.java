package com.example.ch4.json.hw;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class JsonDataExtraction {
    static class UserData {
        String name;
        int age;
        String email;

        public UserData(String name, int age, String email) {
            this.name = name;
            this.age = age;
            this.email = email;
        }

        @Override
        public String toString() {
            return "name: " + name + ", age: " + age + ", email: " + email;
        }
    }

    public static List<UserData> readJson(String filename) {
        Gson gson = new Gson();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return gson.fromJson(sb.toString(), new TypeToken<List<UserData>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void printEmailUP30(List<UserData> userData) {
        if (userData != null) {
            for (UserData user : userData) {
                if (user.age >= 30) {
                    System.out.println(user.name + "의 이메일: " + user.email);
                }
            }
        } else {
            System.out.println("데이터가 없습니다.");
        }
    }

    public static String getResourceFilePath(String fileName) {
        ClassLoader classLoader = JsonDataExtraction.class.getClassLoader();
        if (classLoader.getResource(fileName) != null) {
            return classLoader.getResource(fileName).getPath();
        }
        return null;
    }

    public static void main(String[] args) {
        String inputFilePath = getResourceFilePath("users.json");

        if (inputFilePath != null) {
            List<UserData> userData = readJson(inputFilePath);
            printEmailUP30(userData);
        } else {
            System.out.println("파일을 찾지 못했습니다.");
        }
    }
}
