package com.example.ch4.xls.hw;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CellColorChange {

    static class StudentData {
        String name;
        String subject;
        String score;

        public StudentData(String name, String subject, String score) {
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
                String score = row.getCell(2).getStringCellValue();
                studentData.add(new StudentData(name, subject, score));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return studentData;
    }

    public static void writeExcel(String filePath, List<StudentData> studentData, boolean includeHeader) {
        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fos = new FileOutputStream(filePath)) {
            Sheet sheet = workbook.createSheet();
            int rowIndex = 0;

            if (includeHeader) {
                Row headerRow = sheet.createRow(rowIndex++);
                headerRow.createCell(0).setCellValue("이름");
                headerRow.createCell(1).setCellValue("과목");
                headerRow.createCell(2).setCellValue("점수");
            }

            for (StudentData data : studentData) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(data.name);
                row.createCell(1).setCellValue(data.subject);
                row.createCell(2).setCellValue(data.score);
            }

            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<StudentData> changeColor(String filePath) {
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
                String score = row.getCell(2).getStringCellValue();
                studentData.add(new StudentData(name, subject, score));

                if (score < 50) {
                    Cell scoreCell = row.getCell(2);
                    CellStyle style = workbook.createCellStyle();
                    style.setFillForegroundColor(IndexedColors.RED.getIndex());
                    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    scoreCell.setCellStyle(style);
                }
            }

            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return studentData;
    }

    public static String getResourceFilePath(String fileName) {
        ClassLoader classLoader = CellColorChange.class.getClassLoader();
        if (classLoader.getResource(fileName) != null) {
            return classLoader.getResource(fileName).getPath();
        }
        return null;
    }

    public static void main(String[] args) {
        String inputFilePath = getResourceFilePath("grades.xlsx");

        List<StudentData> studentData = readExcel(inputFilePath);

        if (inputFilePath != null) {
            studentData = changeColor(inputFilePath);
            String outputFilePath = "modified_grades.xlsx";
            writeExcel(outputFilePath, studentData, true);
        } else {
            System.out.println("파일을 찾을 수 없습니다.");
        }
    }
}

