package com.example.ch4.xls.hw;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DataAnalysisInExcel {
    static class StudentScore {
        String studentName;
        List<Integer> scores;

        public StudentScore(String studentName, List<Integer> scores) {
            this.studentName = studentName;
            this.scores = scores;
        }
    }

    public static <Data> List<Data> readExcel(String filePath, Function<Row, Data> rowMapper) {
        List<Data> result = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(filePath))) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            if (rows.hasNext()) rows.next();

            while (rows.hasNext()) {
                Row row = rows.next();
                try {
                    result.add(rowMapper.apply(row));
                } catch (Exception e) {
                    System.err.printf("Row %d 처리 실패: %s%n",
                            row.getRowNum() + 1, e.getMessage());
                }
            }
            return result;
        } catch (IOException e) {
            throw new ExcelUtils.ExcelException("Excel 읽기 오류: " + filePath, e);
        }
    }

    public static <Data> void writeExcel(
            String filePath,
            List<Data> data,
            String[] headers,
            BiConsumer<Data, Row> rowWriter
    ) {
        Path path = Paths.get(filePath);
        if (!path.isAbsolute()) {
            Path resultDir = Paths.get(System.getProperty("user.dir"), "target", "results");
            try {
                Files.createDirectories(resultDir);
            } catch (IOException e) {
                throw new ExcelUtils.ExcelException("결과 디렉토리 생성 실패: " + resultDir, e);
            }
            path = resultDir.resolve(filePath);
        }

        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fos = new FileOutputStream(path.toFile())) {

            Sheet sheet = workbook.createSheet();
            createHeaderRow(sheet.createRow(0), headers);

            int rowIdx = 1;
            for (Data item : data) {
                Row row = sheet.createRow(rowIdx++);
                rowWriter.accept(item, row);
            }
            workbook.write(fos);

        } catch (IOException e) {
            throw new ExcelUtils.ExcelException("Excel 쓰기 오류: " + path, e);
        }
    }

    static void createHeaderRow(Row headerRow, String[] headers) {
        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }
    }

    public static int getMedian(List<Integer> scores) {
        Collections.sort(scores);
        int middle = scores.size() / 2;
        if (scores.size() % 2 == 1) {
            return scores.get(middle);
        } else {
            return (scores.get(middle - 1) + scores.get(middle)) / 2;
        }
    }

    public static StudentScore mapRowToStudent(Row row) {
        String studentName = row.getCell(0).getStringCellValue();
        List<Integer> scores = new ArrayList<>();
        for (int i = 1; i < row.getPhysicalNumberOfCells(); i++) {
            Cell cell = row.getCell(i);
            if (cell.getCellType() == CellType.NUMERIC) {
                scores.add((int) cell.getNumericCellValue());
            } else if (cell.getCellType() == CellType.STRING) {
                try {
                    scores.add(Integer.parseInt(cell.getStringCellValue().trim()));
                } catch (NumberFormatException e) {
                    scores.add(0);
                }
            } else {
                scores.add(0);
            }
        }
        return new StudentScore(studentName, scores);
    }

    public static void writeAnalysisResults(List<StudentScore> studentScores, String outputFilePath) {
        if (studentScores.isEmpty()) {
            System.err.println("No student data found. Cannot perform analysis.");
            return;
        }

        List<String> subjects = new ArrayList<>(studentScores.get(0).scores.size());
        for (int i = 0; i < studentScores.get(0).scores.size(); i++) {
            subjects.add("과목 " + (i + 1));
        }

        List<Map<String, Object>> analysisResults = new ArrayList<>();

        for (int i = 0; i < studentScores.get(0).scores.size(); i++) {
            final int subjectIndex = i;
            List<Integer> subjectScores = studentScores.stream()
                    .map(student -> student.scores.get(subjectIndex))
                    .collect(Collectors.toList());

            double average = subjectScores.stream().mapToInt(Integer::intValue).average().orElse(0);
            int median = getMedian(subjectScores);
            int max = subjectScores.stream().mapToInt(Integer::intValue).max().orElse(0);
            int min = subjectScores.stream().mapToInt(Integer::intValue).min().orElse(0);

            Map<String, Object> subjectStats = new HashMap<>();
            subjectStats.put("과목", "과목 " + (subjectIndex + 1));
            subjectStats.put("평균", average);
            subjectStats.put("중간값", median);
            subjectStats.put("최대값", max);
            subjectStats.put("최소값", min);

            analysisResults.add(subjectStats);
        }

        writeExcel(outputFilePath, analysisResults, new String[]{"과목", "평균", "중간값", "최대값", "최소값"}, (item, row) -> {
            row.createCell(0).setCellValue((String) item.get("과목"));
            row.createCell(1).setCellValue((Double) item.get("평균"));
            row.createCell(2).setCellValue((Integer) item.get("중간값"));
            row.createCell(3).setCellValue((Integer) item.get("최대값"));
            row.createCell(4).setCellValue((Integer) item.get("최소값"));
        });
    }

    public static void main(String[] args) {
        String inputFilePath = ExcelUtils.getResourcePath("test_scores.xlsx");
        String outputFilePath = ExcelUtils.getOutputPath("analysis_data.xlsx");

        List<StudentScore> studentScores = readExcel(inputFilePath, DataAnalysisInExcel::mapRowToStudent);

        writeAnalysisResults(studentScores, outputFilePath);
    }
}

