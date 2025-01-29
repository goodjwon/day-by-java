package com.example.ch4.xls;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;

public class ExcelProcessor {

    static class Employee {
        int id;
        String name;
        int age;
        String department;

        public Employee(int id, String name, int age, String department) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.department = department;
        }

        @Override
        public String toString() {
            return id + "," + name + "," + age + "," + department;
        }
    }

    public static List<Employee> readExcel(String filePath) {
        List<Employee> employees = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            boolean isFirstRow = true;

            for (Row row : sheet) {
                if (isFirstRow) {
                    isFirstRow = false; //헤더 스킵
                    continue;
                }
                int id = (int) row.getCell(0).getNumericCellValue();
                String name = row.getCell(1).getStringCellValue();
                int age = (int) row.getCell(2).getNumericCellValue();
                String department = row.getCell(3).getStringCellValue();
                employees.add(new Employee(id, name, age, department));
            }
        } catch (IOException e) {
            System.out.println("Error readeing Excel file: " + e.getMessage());
        }
        return employees;
    }

    public static void writeExcel(String filePath, List<Employee> employees, boolean includeHeader) {
        try (Workbook workbook = new XSSFWorkbook(); FileOutputStream fos = new FileOutputStream(filePath)) {
            Sheet sheet = workbook.createSheet("Filtered Data");
            int rowIndex = 0;

            if (includeHeader) {
                Row headerRow = sheet.createRow(rowIndex++);
                headerRow.createCell(0).setCellValue("id");
                headerRow.createCell(1).setCellValue("name");
                headerRow.createCell(2).setCellValue("age");
                headerRow.createCell(3).setCellValue("department");
            }

            for (Employee emp : employees) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(emp.id);
                row.createCell(1).setCellValue(emp.name);
                row.createCell(2).setCellValue(emp.age);
                row.createCell(3).setCellValue(emp.department);
            }

            workbook.write(fos);
        } catch (IOException e) {
            System.out.println("Error writing Exel file: " + e.getMessage());
        }
    }

    public static List<Employee> filterByCondition(List<Employee> employees, Predicate<Employee> condition) {
        List<Employee> filtered = new ArrayList<>();
        for (Employee emp : employees) {
            if (condition.test(emp)) {
                filtered.add(emp);
            }
        }
        return filtered;
    }

    public static List<Employee> sortData(List<Employee> employees, Comparator<Employee> comparator) {
        employees.sort(comparator);
        return employees;
    }

    public static String getResourceFilePath(String fileName) {
        ClassLoader classLoader = ExcelProcessor.class.getClassLoader();
        if (classLoader.getResource(fileName) != null) {
            return classLoader.getResource(fileName).getPath();
        }
        return null;
    }

    public static void main(String[] args) {
        String inputFilePath = getResourceFilePath("data.xlsx");
        String outputFilePath = getResourceFilePath("filtered_data.xlsx");

        // 1. 엑셀 읽기
        List<Employee> employees = readExcel(inputFilePath);
        System.out.println("전체 데이터:");
        employees.forEach(System.out::println);

        // 2. 데이터 필터링 (Engineering 부서)
        List<Employee> engineeringEmployees = filterByCondition(employees, emp -> emp.department.equalsIgnoreCase("Engineering"));
        System.out.println("\n필터링된 데이터 (Engineering 부서):");
        engineeringEmployees.forEach(System.out::println);

        // 3. 정렬 (나이 기준 오름차순)
        sortData(engineeringEmployees, Comparator.comparingInt(emp -> emp.age));
        System.out.println("\n정렬된 데이터 (나이 기준 오름차순):");
        engineeringEmployees.forEach(System.out::println);

        // 4. 엑셀 쓰기
        writeExcel(outputFilePath, engineeringEmployees, true);
        System.out.println("\n필터링된 데이터가 '" + outputFilePath + "'에 저장됨");
    }
}
