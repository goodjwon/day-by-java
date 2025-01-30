package com.example.ch4.xls.hw;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IntegrateExcel {
    static class ExcelData {
        int id;
        String name;
        int age;

        public ExcelData(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return id + "," + name + "," + age;
        }
    }

    public static List<ExcelData> readExcel(String filePath) {
        List<ExcelData> excelData = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            boolean isFirstRow = true;

            for (Row row : sheet) {
                if (isFirstRow) {
                    isFirstRow = false;
                    continue;
                }
                int id = (int) row.getCell(0).getNumericCellValue();
                String name = row.getCell(1).getStringCellValue();
                int age = (int) row.getCell(2).getNumericCellValue();
                excelData.add(new ExcelData(id, name, age));
            }
        } catch (IOException e) {
            e.getMessage();
        }
        return excelData;
    }

    public static void writeExcel(String filePath, List<ExcelData> excelData, boolean includeHeader) {
        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fos = new FileOutputStream(filePath)) {
            Sheet sheet = workbook.createSheet();
            int rowIndex = 0;

            if (includeHeader) {
                Row headerRow = sheet.createRow(rowIndex++);
                headerRow.createCell(0).setCellValue("ID");
                headerRow.createCell(1).setCellValue("이름");
                headerRow.createCell(2).setCellValue("나이");
            }

            for (ExcelData xlsx : excelData) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(xlsx.id);
                row.createCell(1).setCellValue(xlsx.name);
                row.createCell(2).setCellValue(xlsx.age);
            }

            workbook.write(fos);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public static String getResourceFilePath(String fileName) {
        ClassLoader classLoader = ExcelData.class.getClassLoader();
        if (classLoader.getResource(fileName) != null) {
            return classLoader.getResource(fileName).getPath();
        }
        return null;
    }

    public static void main(String[] args) {
        String inputFilePath1 = getResourceFilePath("data1.xlsx");
        String inputFilePath2 = getResourceFilePath("data2.xlsx");
        String outputFilePath = getResourceFilePath("add_data.xlsx");

        if (inputFilePath1 == null || inputFilePath2 == null || outputFilePath == null) {
            System.out.println("파일 경로를 찾을 수 없습니다.");
            return;
        }

        List<ExcelData> excelData1 = readExcel(inputFilePath1);
        List<ExcelData> excelData2 = readExcel(inputFilePath2);

        List<ExcelData> margeData = new ArrayList<>(excelData1);
        margeData.addAll(excelData2);

        writeExcel(outputFilePath, margeData, true);

        System.out.println("통합을 완료했습니다");
    }
}
