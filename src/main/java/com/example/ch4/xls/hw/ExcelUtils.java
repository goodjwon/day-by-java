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

    /**
     * 엑셀 처리 과정에서 발생하는 모든 예외를 통합 관리
     * 런타임 예외로 정의하여 호출부에서 예외를 간단히 처리하거나 던질 수 있도록 구현
     */
    public static class ExcelException extends RuntimeException {
        public ExcelException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    /**
     * getResourcePath
     * resources 폴더 내 특정 파일을 검색하여 절대 경로 반환
     * 해당 파일이 없을 경우 ExcelException 던짐
     * getOutputPath
     * 결과 파일을 저장할 디렉토리(target/results)를 생성하고, 최종 파일 경로를 구성
     * 상대 경로를 받은 경우에도 내부적으로 target/results 폴더에 출력 파일을 만들어 냄
     * 이 두 메서드가 엑셀을 읽고 쓰는 경로를 일관성 있게 관리
     * @param fileName
     * @return
     */
    public static String getResourcePath(String fileName) {
        return Optional.ofNullable(ExcelUtils.class.getClassLoader().getResource(fileName))
                .map(URL::getPath)
                .orElseThrow(() -> new ExcelException("Resource not found: " + fileName, null));
    }

    /**
     * readExcel
     * filePath 위치에서 엑셀 파일을 열고, 첫 번째 시트에 대해 순회
     * 각 행(Row)을 받아 rowMapper 함수에 전달하여 원하는 객체(T)로 변환
     * 변환된 객체를 리스트에 담아 반환
     * 행 단위 처리 로직을 유연하게 매핑하기 위해 Function<Row, T> 사용
     * @param filePath
     * @param rowMapper
     * @return
     * @param <T>
     */
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

    /**
     * writeExcel
     * 엑셀 쓰기용 Workbook 생성 후 새로운 시트 생성
     * 헤더를 먼저 작성
     * 나머지 행에 대해 rowWriter(람다나 메서드 참조)를 통해 데이터 매핑
     * filePath가 상대 경로일 경우 target/results 폴더 안에 저장
     * 제네릭으로 구현하여 다양한 타입의 리스트를 처리
     * @param filePath
     * @param data
     * @param headers
     * @param rowWriter
     * @param <T>
     */
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

    // ExcelUtils 클래스 내부에 추가
    public static String getOutputPath(String fileName) {
        Path path = Paths.get(fileName);
        if (!path.isAbsolute()) {
            Path resultDir = Paths.get(System.getProperty("user.dir"), "target", "results");
            try {
                Files.createDirectories(resultDir);
            } catch (IOException e) {
                throw new ExcelException("결과 디렉토리 생성 실패: " + resultDir, e);
            }
            return resultDir.resolve(fileName).toString();
        }
        return fileName;
    }

    /**
     * getStringValue
     * 셀이 BLANK, NUMERIC, STRING 등 어떤 타입이든 일관된 문자열 반환
     * NUMERIC 셀인 경우 문자열로 변환, BLANK인 경우 빈 문자열("")
     * @param row
     * @param index
     * @return
     */
    public static String getStringValue(Row row, int index) {
        return Optional.ofNullable(row.getCell(index))
                .map(c -> {
                    CellType cellType = c.getCellType();

                    // 1. 빈 셀 처리
                    if (cellType == CellType.BLANK) {
                        return "";
                    }

                    // 2. 숫자 셀 처리
                    if (cellType == CellType.NUMERIC) {
                        return String.valueOf((int) c.getNumericCellValue());
                    }

                    // 3. 문자열 셀 처리
                    return c.getStringCellValue().trim();
                })
                .orElse(""); // 셀이 아예 없는 경우
    }

    /**
     * getIntValue
     * getNumericValue 결과를 int로 형변환
     * @param row
     * @param index
     * @return
     */
    public static int getIntValue(Row row, int index) {
        return (int) getNumericValue(row, index);
    }

    /**
     * getNumericValue
     * 셀이 NUMERIC, STRING, FORMULA, BLANK인 상황을 모두 처리해 double 반환
     * 내부적으로 parseStringToDouble 메서드를 사용하여 문자열도 안전하게 변환
     * @param row
     * @param index
     * @return
     */
    public static double getNumericValue(Row row, int index) {
        Cell cell = row.getCell(index, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

        // 디버깅 로그 추가 (실제 배포시 제거)
        System.out.printf("[DEBUG] Row %d Cell %d - Type: %s%n",
                row.getRowNum()+1, index, cell.getCellType());

        switch (cell.getCellType()) {
            case BLANK:
                return 0.0;

            case FORMULA:
                switch (cell.getCachedFormulaResultType()) {
                    case NUMERIC: return cell.getNumericCellValue();
                    case STRING: return parseStringToDouble(cell.getStringCellValue());
                    default: return 0.0;
                }

            case STRING:
                return parseStringToDouble(cell.getStringCellValue().trim());

            case NUMERIC:
                return cell.getNumericCellValue();

            default:
                return 0.0;
        }
    }

    /**
     * 문자열 상태의 숫자를 double로 변환하는 보조 로직
     * 변환 실패 시 에러 메시지 출력 후 0.0 반환
     * 숫자가 아닌 값이 섞여 있어도 프로세스가 중단되지 않도록 예외 처리
     * @param value
     * @return
     */
    private static double parseStringToDouble(String value) {
        if (value.isEmpty()) return 0.0;
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            System.err.printf("숫자 변환 실패: '%s'%n", value);
            return 0.0;
        }
    }

    /**
     * 새로 생성하는 엑셀 파일에 첫 번째 행을 헤더로 초기화
     * writeExcel 메서드 내부에서만 사용되어, 코드 분리를 통해 가독성 높임
     * @param headerRow
     * @param headers
     */
    static void createHeaderRow(Row headerRow, String[] headers) {
        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }
    }
}
