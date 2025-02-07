package com.example.ch4.xls.hw;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class FillSellNA {
    static class SurveyData {
        private String question1;
        private String question2;
        private String question3;

        public SurveyData(String question1, String question2, String question3) {
            this.question1 = question1;
            this.question2 = question2;
            this.question3 = question3;
        }

        public String getQuestion1() {
            return question1;
        }

        public String getQuestion2() {
            return question2;
        }

        public String getQuestion3() {
            return question3;
        }
    }

    public static List<SurveyData> readExcel(String filePath, Function<Row, SurveyData> rowMapper) {
        List<SurveyData> result = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(filePath))) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            if (rows.hasNext()) rows.next();

            while (rows.hasNext()) {
                Row row = rows.next();
                try {
                    result.add(rowMapper.apply(row));
                } catch (Exception e) {
                    System.err.printf("Row %d 처리 실패: %s%n", row.getRowNum() + 1, e.getMessage());
                }
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException("Excel 읽기 오류: " + filePath, e);
        }
    }

    public static void writeExcel(String filePath, List<SurveyData> data, String[] headers, BiConsumer<SurveyData, Row> rowWriter) {
        Path path = Paths.get(filePath);
        if (!path.isAbsolute()) {
            Path resultDir = Paths.get(System.getProperty("user.dir"), "target", "results");
            try {
                Files.createDirectories(resultDir);
            } catch (IOException e) {
                throw new RuntimeException("결과 디렉토리 생성 실패: " + resultDir, e);
            }
            path = resultDir.resolve(filePath);
        }

        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fos = new FileOutputStream(path.toFile())) {

            Sheet sheet = workbook.createSheet();
            createHeaderRow(sheet.createRow(0), headers);

            int rowIdx = 1;
            for (SurveyData item : data) {
                Row row = sheet.createRow(rowIdx++);
                rowWriter.accept(item, row);
            }
            workbook.write(fos);

        } catch (IOException e) {
            throw new RuntimeException("Excel 쓰기 오류: " + path, e);
        }
    }

    private static void createHeaderRow(Row headerRow, String[] headers) {
        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }
    }

    private static SurveyData RowToSurveyData(Row row) {
        String question1 = getCellValue(row, 0);
        String question2 = getCellValue(row, 1);
        String question3 = getCellValue(row, 2);

        return new SurveyData(question1, question2, question3);
    }

    private static String getCellValue(Row row, int columnIndex) {
        Cell cell = row.getCell(columnIndex);
        if (cell == null || cell.getCellType() == CellType.BLANK) {
            return "N/A";
        }
        return cell.toString();
    }

    public static void main(String[] args) {
        String inputFilePath = ExcelUtils.getResourcePath("survey_data.xlsx");
        String outputFilePath = ExcelUtils.getOutputPath("fill_survey_data.xlsx");

        List<SurveyData> surveyDataList = readExcel(inputFilePath, FillSellNA::RowToSurveyData);

        String[] headers = {"Question 1", "Question 2", "Question 3"};

        writeExcel(outputFilePath, surveyDataList, headers, (data, row) -> {
            row.createCell(0).setCellValue(data.getQuestion1());
            row.createCell(1).setCellValue(data.getQuestion2());
            row.createCell(2).setCellValue(data.getQuestion3());
        });

        System.out.println("엑셀 파일이 저장되었습니다: " + outputFilePath);
    }
}

