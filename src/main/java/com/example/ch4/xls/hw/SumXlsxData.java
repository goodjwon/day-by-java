package com.example.ch4.xls.hw;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SumXlsxData {
    static class SalesData {
        String month;
        int sales;

        public SalesData(String month, int sales) {
            this.month = month;
            this.sales = sales;
        }

        @Override
        public String toString() {
            return month + "," + sales;
        }
    }

    public static List<SalesData> readExel(String filePath) {
        List<SalesData> salesData = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)){
            Sheet sheet = workbook.getSheetAt(0);
            boolean isFirstRow = true;

            for (Row row : sheet) {
                if (isFirstRow) {
                    isFirstRow = false;
                    continue;
                }
                String month = row.getCell(0).getStringCellValue();
                int sales = (int) row.getCell(1).getNumericCellValue();
                salesData.add(new SalesData(month, sales));
            }

        } catch (IOException e) {
            e.getMessage();
        }
        return salesData;
    }

    public static void writeExcel(String filePath, List<SalesData> salesData, boolean includeHeader) {
        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fos = new FileOutputStream(filePath)){
             Sheet sheet = workbook.createSheet("Sum Data");
             int rowIndex = 0;

             if (includeHeader) {
                 Row headerRow = sheet.createRow(rowIndex++);
                 headerRow.createCell(0).setCellValue("월");
                 headerRow.createCell(1).setCellValue("매출액");
             }

             for (SalesData sela : salesData) {
                 Row row = sheet.createRow(rowIndex++);
                 row.createCell(0).setCellValue(sela.month);
                 row.createCell(1).setCellValue(sela.sales);
             }

             workbook.write(fos);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public static List<SalesData> calculateMonthlySales(List<SalesData> salesData) {
        Map<String, Integer> salesSumMap = new HashMap<>();
        for (SalesData data : salesData) {
            salesSumMap.put(data.month, salesSumMap.getOrDefault(data.month, 0) + data.sales);
        }

        List<SalesData> summedData = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : salesSumMap.entrySet()) {
            summedData.add(new SalesData(entry.getKey(), entry.getValue()));
        }

        return summedData;
    }

    public static String getResourceFilePath(String fileName) {
        ClassLoader classLoader = SumXlsxData.class.getClassLoader();
        if (classLoader.getResource(fileName) != null) {
            return classLoader.getResource(fileName).getPath();
        }
        return null;
    }

    public static void main(String[] args) {
        String inputFilePath = getResourceFilePath("sales_data.xlsx");
        String outputFilePath = getResourceFilePath("sum_data.xlsx");

        if (inputFilePath == null) {
            System.out.println("입력 파일을 찾을 수 없습니다.");
            return;
        }

        List<SalesData> salesData = readExel(inputFilePath);
        salesData.forEach(System.out::println);

        List<SalesData> sumData = calculateMonthlySales(salesData);

        writeExcel(outputFilePath, sumData, true);
    }
}
