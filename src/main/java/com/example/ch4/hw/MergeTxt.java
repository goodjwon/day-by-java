package com.example.ch4.hw;


import java.io.*;

import static com.example.ch4.xls.ExcelProcessor.getResourceFilePath;

public class MergeTxt {
    public static void main(String[] args) {
        String file1 = getResourceFilePath("file1.txt");
        String file2 = getResourceFilePath("file2.txt");
        String outputFile = getResourceFilePath("file.txt");

        BufferedReader br1 = null;
        BufferedReader br2 = null;
        BufferedWriter bw = null;

        try {
            br1 = new BufferedReader(new FileReader(file1));
            br2 = new BufferedReader(new FileReader(file2));
            bw = new BufferedWriter(new FileWriter(outputFile));

            String line;
            while ((line = br1.readLine()) != null) {
                bw.write(line);
                bw.newLine();
            }

            while ((line = br2.readLine()) != null) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br1 != null) {
                    br1.close();
                }
                if (br2 != null) {
                    br2.close();
                }
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
