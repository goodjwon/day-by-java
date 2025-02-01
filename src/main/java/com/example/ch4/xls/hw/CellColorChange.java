package com.example.ch4.xls.hw;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CellColorChange {

    static class StudentData {
        String name;
        String subject;
        double score;

        public StudentData(String name, String subject, double score) {
            this.name = name;
            this.subject = subject;
            this.score = score;
        }
    }

    public static List<StudentData> changeColor(String filePath) {
        List<StudentData> studentData = new ArrayList<>();
        Workbook workbook = null;

        try (FileInputStream fis = new FileInputStream(filePath)) {
            // 1. 워크북 로드 및 스타일 생성
            workbook = new XSSFWorkbook(fis);
            CellStyle redStyle = createRedCellStyle(workbook);

            // 2. 데이터 처리 및 스타일 적용
            processWorkbook(workbook, studentData, redStyle);

            // 3. 변경 사항 저장
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }

        } catch (IOException e) {
            throw new ExcelUtils.ExcelException("파일 처리 실패: " + filePath, e);
        } finally {
            closeWorkbook(workbook);
        }
        return studentData;
    }

    private static void processWorkbook(
            Workbook workbook,
            List<StudentData> dataList,
            CellStyle redStyle
    ) {
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rows = sheet.iterator();
        if (rows.hasNext()) rows.next(); // 헤더 스킵

        while (rows.hasNext()) {
            Row row = rows.next();
            processRow(row, dataList, redStyle);
        }
    }

    private static void processRow(
            Row row,
            List<StudentData> dataList,
            CellStyle redStyle
    ) {
        try {
            // 엑셀 유틸리티로 데이터 추출
            String name = ExcelUtils.getStringValue(row, 0);
            String subject = ExcelUtils.getStringValue(row, 1);
            double score = ExcelUtils.getNumericValue(row, 2);

            dataList.add(new StudentData(name, subject, score));

            // 조건에 따른 스타일 적용
            if (score < 50) {
                Cell scoreCell = row.getCell(2);
                if (scoreCell != null) {
                    scoreCell.setCellStyle(redStyle);
                }
            }
        } catch (Exception e) {
            System.err.printf("Row %d 처리 실패: %s%n",
                    row.getRowNum() + 1, e.getMessage());
        }
    }

    private static CellStyle createRedCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return style;
    }

    private static void closeWorkbook(Workbook workbook) {
        try {
            if (workbook != null) {
                workbook.close();
            }
        } catch (IOException e) {
            System.err.println("워크북 닫기 오류: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            // 1. 리소스 경로 조회
            String inputPath = ExcelUtils.getResourcePath("grades.xlsx");
            String outputPath = "modified_grades.xlsx";

            // 2. 파일 처리 (원본 파일 수정)
            List<StudentData> data = changeColor(inputPath);

            // 3. 새로운 파일 생성 (유틸리티 사용)
            ExcelUtils.writeExcel(
                    outputPath,
                    data,
                    new String[]{"이름", "과목", "점수"},
                    (student, row) -> {
                        row.createCell(0).setCellValue(student.name);
                        row.createCell(1).setCellValue(student.subject);
                        row.createCell(2).setCellValue(student.score);
                    }
            );

            System.out.println("처리 완료! 결과 파일: " + outputPath);

        } catch (ExcelUtils.ExcelException e) {
            System.err.println("오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
}