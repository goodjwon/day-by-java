package com.example.ch4.files.io;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterExample {
    public static void main(String[] args) {
        String filename = "output.txt";

        try (FileWriter fw = new FileWriter(filename)) {
            fw.write("문자 기반 출력\n");
            fw.write("두번째 줄 ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
