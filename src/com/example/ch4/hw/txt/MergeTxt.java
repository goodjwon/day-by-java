package com.example.ch4.hw.txt;


import java.io.*;

public class MergeTxt {
    public static void main(String[] args) {
        String file1 = "file1.txt";
        String file2 = "file2.txt";

        try (PrintWriter pw = new PrintWriter(file1)) {
            pw.println("Hello from file1");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter pw = new PrintWriter(file2)){
            pw.println("Hello from file2");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (){

        }
    }
}
