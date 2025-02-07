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
import java.util.function.Predicate;

public class FilteredData {
    static class FilterData {
        String name;
        String employee;
        int price;

        public FilterData(String name, String employee, int price) {
            this.name = name;
            this.employee = employee;
            this.price = price;
        }

        @Override
        public String toString() {
            return name + "," + employee + "," + price;
        }
    }

    //엑셀읽기
    public static List<FilterData> readExel(String filePath) {
        List<FilterData> filterData = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            boolean isFirstRow = true;

            for (Row row : sheet) {
                if (isFirstRow) {
                    isFirstRow = false;
                    continue;
                }
                String name = row.getCell(0).getStringCellValue();
                String employee = row.getCell(1).getStringCellValue();
                double price = row.getCell(2).getNumericCellValue();
                int salary = (int) price;
                filterData.add(new FilterData(name, employee, salary));
            }
        } catch (IOException e) {
            e.getMessage();
        }
        return filterData;
    }

    //엑셀쓰기
    public static void writeExcel(String filePath, List<FilterData> filterData, boolean includeHeader) {
        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fos = new FileOutputStream(filePath)){
            Sheet sheet = workbook.createSheet("Filter Data");
            int rowIndex = 0;

            if (includeHeader) {
                Row headerRow = sheet.createRow(rowIndex++);
                headerRow.createCell(0).setCellValue("이름");
                headerRow.createCell(1).setCellValue("직종");
                headerRow.createCell(2).setCellValue("연봉");
            }

            for (FilterData filter : filterData) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(filter.name);
                row.createCell(1).setCellValue(filter.employee);
                row.createCell(2).setCellValue(filter.price);
            }

            workbook.write(fos);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    //필터링
    public static List<FilterData> filterByCondition(List<FilterData> filterData, Predicate<FilterData> condition) {
        List<FilterData> filtered = new ArrayList<>();
        for (FilterData fil : filterData) {
            if (condition.test(fil)) {
                filtered.add(fil);
            }
        }
        return filtered;
    }

    public static String getResourceFilePath(String fileName) {
        ClassLoader classLoader = FilteredData.class.getClassLoader();
        if (classLoader.getResource(fileName) != null) {
            return classLoader.getResource(fileName).getPath();
        }
        return null;
    }

    //메인
    public static void main(String[] args) {
        String inputFilePath = getResourceFilePath("employee_data.xlsx");
        String outputFilePath = getResourceFilePath("filter_data.xlsx");

        List<FilterData> filterData = readExel(inputFilePath);

        List<FilterData> filteredData = filterByCondition(filterData, fil -> fil.price >= 50_000_000);

        filteredData.forEach(System.out::println);

        writeExcel(outputFilePath, filteredData, true);
    }
}