package com.example.ch4.xls.hw;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class CellColorChange {

    static class StudentData {
        String name;
        String subject;
        Double score;

        public StudentData(String name, String subject, Double score) {
            this.name = name;
            this.subject = subject;
            this.score = score;
        }

        // 불필요. jwon

//        @Override
//        public String toString() {
//            return name + "," + subject + "," + score;
//        }
    }

    // 불필요. jwon
//    public static List<StudentData> readExcel(String filePath) {
//        List<StudentData> studentData = new ArrayList<>();
//        try (FileInputStream fis = new FileInputStream(filePath);
//             Workbook workbook = new XSSFWorkbook(fis)) {
//
//            Sheet sheet = workbook.getSheetAt(0);
//            Iterator<Row> rowIterator = sheet.iterator();
//
//            // 헤더 행 건너뛰기 (헤더가 없는 경우 처리)
//            if (rowIterator.hasNext()) rowIterator.next();
//
//            while (rowIterator.hasNext()) {
//                Row row = rowIterator.next();
//                try {
//                    // Null-safe cell 접근
//                    String name = getCellStringValue(row, 0, "Name");
//                    String subject = getCellStringValue(row, 1, "Subject");
//                    double score = getCellNumericValue(row, 2, "Score");
//
//                    studentData.add(new StudentData(name, subject, score));
//                } catch (InvalidDataException e) {
//                    System.err.printf("Row %d 오류: %s%n", row.getRowNum()+1, e.getMessage());
//                }
//            }
//        } catch (IOException | RuntimeException e) {
//            throw new ExcelReadException("Excel 파일 처리 실패: " + filePath, e);
//        }
//        return studentData;
//    }

//    private static String getCellStringValue(Row row, int cellIdx, String fieldName) {
//        Cell cell = row.getCell(cellIdx, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
//        if (cell == null) {
//            throw new InvalidDataException(fieldName + " 셀이 존재하지 않음");
//        }
//        return switch (cell.getCellType()) {
//            case STRING -> cell.getStringCellValue().trim();
//            case NUMERIC -> String.valueOf(cell.getNumericCellValue());
//            case BLANK -> "";
//            default -> throw new InvalidDataException(fieldName + " 타입 오류 (문자/숫자 셀 아님)");
//        };
//    }

//    private static double getCellNumericValue(Row row, int cellIdx, String fieldName) {
//        Cell cell = row.getCell(cellIdx, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
//        if (cell == null) {
//            throw new InvalidDataException(fieldName + " 셀이 존재하지 않음");
//        }
//        return switch (cell.getCellType()) {
//            case NUMERIC -> cell.getNumericCellValue();
//            case STRING -> parseStringToDouble(cell.getStringCellValue(), fieldName);
//            default -> throw new InvalidDataException(fieldName + " 타입 오류 (숫자 셀 아님)");
//        };
//    }

    // 숫자 파싱 유틸리티
//    private static double parseStringToDouble(String value, String fieldName) {
//        try {
//            return Double.parseDouble(value.trim());
//        } catch (NumberFormatException e) {
//            throw new InvalidDataException(fieldName + " 숫자 변환 실패: " + value);
//        }
//    }

    // 커스텀 예외 클래스
//    static class ExcelReadException extends RuntimeException {
//        public ExcelReadException(String message, Throwable cause) {
//            super(message, cause);
//        }
//    }
//
//    static class InvalidDataException extends RuntimeException {
//        public InvalidDataException(String message) {
//            super(message);
//        }
//    }


//    public static void writeExcel(String filePath, List<StudentData> studentData, boolean includeHeader) {
//        try (Workbook workbook = new XSSFWorkbook();
//             FileOutputStream fos = new FileOutputStream(filePath)) {
//            Sheet sheet = workbook.createSheet();
//            int rowIndex = 0;
//
//            if (includeHeader) {
//                Row headerRow = sheet.createRow(rowIndex++);
//                headerRow.createCell(0).setCellValue("이름");
//                headerRow.createCell(1).setCellValue("과목");
//                headerRow.createCell(2).setCellValue("점수");
//            }
//
//            for (StudentData data : studentData) {
//                Row row = sheet.createRow(rowIndex++);
//                row.createCell(0).setCellValue(data.name);
//                row.createCell(1).setCellValue(data.subject);
//                row.createCell(2).setCellValue(data.score);
//            }

//            workbook.write(fos);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    public static List<StudentData> changeColor(String filePath) {
//        List<StudentData> studentData = new ArrayList<>();
//
//        // 1. 워크북 로드 방식 변경 (try-with-resources 분리)
//        Workbook workbook;
//        try (FileInputStream fis = new FileInputStream(filePath)) {
//            workbook = new XSSFWorkbook(fis);
//        } catch (IOException e) {
//            throw new RuntimeException("Excel 파일 읽기 실패: " + filePath, e);
//        }
//
//        try {
//            Sheet sheet = workbook.getSheetAt(0);
//            CellStyle redStyle = createRedCellStyle(workbook); // 스타일 재사용
//
//            Iterator<Row> rowIterator = sheet.iterator();
//            if (rowIterator.hasNext()) rowIterator.next(); // 헤더 스킵
//
//            while (rowIterator.hasNext()) {
//                Row row = rowIterator.next();
//                processRow(row, studentData, redStyle);
//            }
//
//            // 2. 출력 스트림 분리 처리
//            saveModifiedWorkbook(workbook, filePath);
//
//        } finally {
//            closeWorkbook(workbook); // 리소스 정리
//        }
//
//        return studentData;
//    }

    // 스타일 생성 메서드
//    private static CellStyle createRedCellStyle(Workbook workbook) {
//        CellStyle style = workbook.createCellStyle();
//        style.setFillForegroundColor(IndexedColors.RED.getIndex());
//        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//        return style;
//    }
//
//    // 행 처리 메서드
//    private static void processRow(Row row, List<StudentData> data, CellStyle redStyle) {
//        try {
//            String name = getCellValueAsString(row, 0, "이름");
//            String subject = getCellValueAsString(row, 1, "과목");
//            double score = getCellValueAsDouble(row, 2, "점수");
//
//            data.add(new StudentData(name, subject, score));
//
//            if (score < 50) {
//                applyCellStyle(row.getCell(2), redStyle);
//            }
//        } catch (DataProcessingException e) {
//            System.err.printf("행 %d 처리 실패: %s%n", row.getRowNum()+1, e.getMessage());
//        }
//    }

    // 파일 저장 메서드
//    private static void saveModifiedWorkbook(Workbook workbook, String path) {
//        try (FileOutputStream fos = new FileOutputStream(path)) {
//            workbook.write(fos);
//        } catch (IOException e) {
//            throw new RuntimeException("Excel 파일 저장 실패: " + path, e);
//        }
//    }

    // 유틸리티 메서드들
//    private static String getCellValueAsString(Row row, int idx, String fieldName) {
//        Cell cell = row.getCell(idx, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
//        if (cell == null) throw new DataProcessingException(fieldName + " 셀 누락");
//
//        return switch (cell.getCellType()) {
//            case STRING -> cell.getStringCellValue().trim();
//            case NUMERIC -> String.valueOf((int) cell.getNumericCellValue());
//            default -> throw new DataProcessingException(fieldName + " 유효하지 않은 셀 타입");
//        };
//    }

//    private static double getCellValueAsDouble(Row row, int idx, String fieldName) {
//        Cell cell = row.getCell(idx, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
//        if (cell == null) throw new DataProcessingException(fieldName + " 셀 누락");
//
//        return switch (cell.getCellType()) {
//            case NUMERIC -> cell.getNumericCellValue();
//            case STRING -> parseDouble(cell.getStringCellValue(), fieldName);
//            default -> throw new DataProcessingException(fieldName + " 유효하지 않은 셀 타입");
//        };
//    }

//    private static double parseDouble(String value, String fieldName) {
//        try {
//            return Double.parseDouble(value.trim());
//        } catch (NumberFormatException e) {
//            throw new DataProcessingException(fieldName + " 숫자 변환 실패: " + value);
//        }
//    }

//    private static void applyCellStyle(Cell cell, CellStyle style) {
//        if (cell != null) {
//            cell.setCellStyle(style);
//        }
//    }
//
//    private static void closeWorkbook(Workbook workbook) {
//        try {
//            if (workbook != null) {
//                workbook.close();
//            }
//        } catch (IOException e) {
//            System.err.println("워크북 닫기 실패: " + e.getMessage());
//        }
//    }
//
//    // 커스텀 예외 클래스
//    static class DataProcessingException extends RuntimeException {
//        DataProcessingException(String message) {
//            super(message);
//        }
//    }

//    public static String getResourceFilePath(String fileName) {
//        ClassLoader classLoader = CellColorChange.class.getClassLoader();
//        if (classLoader.getResource(fileName) != null) {
//            return classLoader.getResource(fileName).getPath();
//        }
//        return null;
//    }
//
//    public static String getResourceFilePathFunction(String fileName) {
//        return Optional.ofNullable(CellColorChange.class.getClassLoader().getResource(fileName))
//                .map(URL::getPath)
//                .orElseThrow(() -> new IllegalArgumentException("Resource not found: " + fileName));
//    }
//
//    public static void debugResourceLoading(String fileName) {
//        URL url = CellColorChange.class.getClassLoader().getResource(fileName);
//        System.out.println("Resource URL: " + url);
//
//        if (url != null) {
//            System.out.println("File exists: " + new File(url.getFile()).exists());
//        }
//    }

    public static void main(String[] args) {
//        debugResourceLoading("grades.xlsx");
//
//        String inputFilePath = getResourceFilePathFunction("grades.xlsx");
//
//
//        List<StudentData> studentData = readExcel(inputFilePath);
//
//        if (inputFilePath != null) {
//            studentData = changeColor(inputFilePath);
//            String outputFilePath = "modified_grades.xlsx";
//            writeExcel(outputFilePath, studentData, true);
//        } else {
//            System.out.println("파일을 찾을 수 없습니다.");
//        }

        try {
            String inputPath = ExcelUtils.getResourcePath("grades.xlsx");
            List<StudentData> data = processWorkbook(inputPath);

            // ExcelUtils를 이용해 데이터 검증 및 저장 (필요시 사용)
            /*
            ExcelUtils.writeExcel(
                    "validated_grades.xlsx",
                    data,
                    new String[]{"이름", "과목", "점수"},
                    (student, row) -> {
                        row.createCell(0).setCellValue(student.name);
                        row.createCell(1).setCellValue(student.subject);
                        row.createCell(2).setCellValue(student.score);
                    }
            );
            */

            System.out.println("처리 완료! 결과 파일: " + ExcelUtils.getOutputPath("modified_grades.xlsx"));

        } catch (ExcelUtils.ExcelException e) {
            System.err.println("오류 발생: " + e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * XSSFCellStyle 인스턴스 생성
     * XSSFColor를 사용해 RGB(255,0,0) 값을 넣어 빨간색 지정
     * 스타일 패턴(SOLID_FOREGROUND)을 적용해서 셀에 완전한 배경색이 들어가도록 설정
     * @param workbook
     * @return
     */
    private static XSSFCellStyle createRedCellStyle(Workbook workbook) {
        XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
        XSSFColor redColor = new XSSFColor(new java.awt.Color(255, 0, 0), new DefaultIndexedColorMap());
        style.setFillForegroundColor(redColor);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return style;
    }

    /**
     * 현재 행(Row)의 이름, 과목, 점수 데이터를 추출
     * 세 번째 셀(점수 셀)이 없거나 빈 셀이면 Row.MissingCellPolicy.CREATE_NULL_AS_BLANK 옵션으로 생성 후 0으로 초기화
     * scoreCell.setCellType(CellType.NUMERIC) 후 scoreCell.setCellValue(0.0) 호출로 실제 숫자 셀로 만든 뒤 0 대입
     * 그 후 ExcelUtils.getNumericValue로 점수(double) 추출
     * 점수가 50 미만이면 redStyle을 셀에 적용
     * @param row
     * @param dataList
     * @param redStyle
     */
    private static void processRow(
            Row row,
            List<StudentData> dataList,
            CellStyle redStyle
    ) {
        try {
            String name = ExcelUtils.getStringValue(row, 0);
            String subject = ExcelUtils.getStringValue(row, 1);

            // 점수 셀 강제 생성
            Cell scoreCell = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

            // 빈 셀이면 0으로 초기화
            if (scoreCell.getCellType() == CellType.BLANK) {
                scoreCell.setCellType(CellType.NUMERIC);
                scoreCell.setCellValue(0.0);
            }

            double score = ExcelUtils.getNumericValue(row, 2);

            dataList.add(new StudentData(name, subject, score));

            // 50 미만이면 빨간색
            if (score < 50) {
                scoreCell.setCellStyle(redStyle);
            }

        } catch (Exception e) {
            System.err.printf("Row %d 처리 실패: %s%n",
                    row.getRowNum() + 1, e.getMessage());
        }
    }

    public static List<StudentData> processWorkbook(String inputPath) {
        List<StudentData> studentData = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(inputPath))) {
            CellStyle redStyle = createRedCellStyle(workbook);
            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rows = sheet.iterator();
            if (rows.hasNext()) rows.next(); // 헤더 스킵

            while (rows.hasNext()) {
                Row row = rows.next();
                processRow(row, studentData, redStyle);
            }

            // 변경 사항을 새로운 파일로 저장
            String outputPath = ExcelUtils.getOutputPath("modified_grades.xlsx");
            try (FileOutputStream fos = new FileOutputStream(outputPath)) {
                workbook.write(fos);
            }

        } catch (IOException e) {
            throw new ExcelUtils.ExcelException("파일 처리 실패: " + inputPath, e);
        }
        return studentData;
    }

}

