package com.example.ch4.xls.hw;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class ExcelUtils {

    // 공통 예외 클래스
    public static class ExcelException extends RuntimeException {
        public ExcelException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    // 리소스 경로 조회
    public static String getResourcePath(String fileName) {
        return Optional.ofNullable(ExcelUtils.class.getClassLoader().getResource(fileName))
                .map(URL::getPath)
                .orElseThrow(() -> new ExcelException("Resource not found: " + fileName, null));
    }

    // 엑셀 파일 읽기 (제네릭 + 함수형 인터페이스, 처음 T는 선언, List T는 반환 받을 타입.)
    public static <T> List<T> readExcel(String filePath, Function<Row, T> rowMapper) {
        List<T> result = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(filePath))) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            if (rows.hasNext()) rows.next(); // 헤더 스킵

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
            throw new ExcelException("Excel 읽기 오류: " + filePath, e);
        }
    }

    // 엑셀 파일 쓰기 (제네릭 + 헤더/데이터 매핑)
    // ---> 수정 포인트: filePath가 "상대 경로"라면 target/results 밑에 저장하도록 변경
    public static <T> void writeExcel(
            String filePath,
            List<T> data,
            String[] headers,
            BiConsumer<T, Row> rowWriter
    ) {
        // 1) filePath가 절대 경로인지 체크
        Path path = Paths.get(filePath);
        if (!path.isAbsolute()) {
            // 절대 경로가 아니라면, target/results 폴더 밑에 저장하도록 경로 재설정
            Path resultDir = Paths.get(System.getProperty("user.dir"), "target", "results");
            try {
                Files.createDirectories(resultDir); // 폴더가 없으면 생성
            } catch (IOException e) {
                throw new ExcelException("결과 디렉토리 생성 실패: " + resultDir, e);
            }
            path = resultDir.resolve(filePath); // target/results/filePath
        }

        // 2) 파일에 엑셀을 쓰는 로직은 동일
        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fos = new FileOutputStream(path.toFile())) {

            Sheet sheet = workbook.createSheet();
            createHeaderRow(sheet.createRow(0), headers);

            int rowIdx = 1;
            for (T item : data) {
                Row row = sheet.createRow(rowIdx++);
                rowWriter.accept(item, row);
            }
            workbook.write(fos);

        } catch (IOException e) {
            throw new ExcelException("Excel 쓰기 오류: " + path, e);
        }
    }

    // 셀 값 읽기 유틸리티
    public static String getStringValue(Row row, int index) {
        return Optional.ofNullable(row.getCell(index))
                .map(c -> {
                    if (c.getCellType() == CellType.NUMERIC) {
                        return String.valueOf((int) c.getNumericCellValue());
                    }
                    return c.getStringCellValue().trim();
                })
                .orElse("");
    }

    public static int getIntValue(Row row, int index) {
        return (int) getNumericValue(row, index);
    }

    public static double getNumericValue(Row row, int index) {
        return Optional.ofNullable(row.getCell(index))
                .map(c -> {
                    if (c.getCellType() == CellType.STRING) {
                        return Double.parseDouble(c.getStringCellValue().trim());
                    }
                    return c.getNumericCellValue();
                })
                .orElse(0.0);
    }

    private static void createHeaderRow(Row headerRow, String[] headers) {
        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }
    }
}
