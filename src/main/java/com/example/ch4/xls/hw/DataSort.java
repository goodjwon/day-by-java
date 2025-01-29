package com.example.ch4.xls.hw;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class DataSort {
    static class StudentData {
        String name;
        String subject;
        int score;

        public StudentData(String name, String subject, int score) {
            this.name = name;
            this.subject = subject;
            this.score = score;
        }

        @Override
        public String toString() {
            return name + "," + subject + "," + score;
        }
    }

    public static List<StudentData> readExcel(String filePath) {
        List<StudentData> studentData = new ArrayList<>();
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
                String subject = row.getCell(1).getStringCellValue();
                int score = (int) row.getCell(2).getNumericCellValue();
                studentData.add(new StudentData(name, subject, score));
            }
        } catch (IOException e) {
            e.getMessage();
        }

        return studentData;
    }

    public static void writeExcel(String filePath, List<StudentData> studentData, boolean includeHeader) {
        try (Workbook workbook = new XSSFWorkbook(); FileOutputStream fos = new FileOutputStream(filePath)) {
            Sheet sheet = workbook.createSheet("Sort Data");
            int rowIndex = 0;

            if (includeHeader) {
                Row headerRow = sheet.createRow(rowIndex++);
                headerRow.createCell(0).setCellValue("이름");
                headerRow.createCell(1).setCellValue("과목");
                headerRow.createCell(2).setCellValue("점수");
            }

            for (StudentData stu : studentData) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(stu.name);
                row.createCell(1).setCellValue(stu.subject);
                row.createCell(2).setCellValue(stu.score);
            }

            workbook.write(fos);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public static List<StudentData> filterByCondition(List<StudentData> studentData, Predicate<StudentData> condition) {
        List<StudentData> filtered = new ArrayList<>();
        for (StudentData stu : studentData) {
            if (condition.test(stu)) {
                filtered.add(stu);
            }
        }
        return filtered;
    }

    public static List<StudentData> sortData(List<StudentData> studentData, Comparator<StudentData> comparator) {
        studentData.sort(comparator);
        return studentData;
    }

    public static String getResourceFilePath(String fileName) {
        ClassLoader classLoader = DataSort.class.getClassLoader();
        if (classLoader.getResource(fileName) != null) {
            return classLoader.getResource(fileName).getPath();
        }
        return null;
    }

    public static void main(String[] args) {
        String inputFilePath = getResourceFilePath("students_scores.xlsx");
        if (inputFilePath == null) {
            System.out.println("파일을 찾을 수 없습니다.");
            return;
        }

        List<StudentData> studentData = readExcel(inputFilePath);

        studentData = sortData(studentData, (s1, s2) -> Integer.compare(s2.score, s1.score));

        List<StudentData> top10Students = studentData.subList(0, Math.min(10, studentData.size()));

        for (StudentData student : top10Students) {
            System.out.println(student);
        }

        String outputFilePath = getResourceFilePath("sorted_top10_students.xlsx");
        if (outputFilePath != null) {
            writeExcel(outputFilePath, top10Students, true);
        } else {
            System.out.println("출력 파일 경로를 찾을 수 없습니다.");
        }
    }
}
