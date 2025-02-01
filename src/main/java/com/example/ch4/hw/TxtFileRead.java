package com.example.ch4.hw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static com.example.ch4.xls.ExcelProcessor.getResourceFilePath;

public class TxtFileRead {
    public static void main(String[] args) {
        String filename = getResourceFilePath("employees.txt");

        try (FileWriter fw = new FileWriter(filename)) {
            fw.write("John, 28\n");
            fw.write("Jane, 35\n");
            fw.write("Alice, 24\n");
            fw.write("Bob, 30\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) { // 한 줄씩 읽기
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
