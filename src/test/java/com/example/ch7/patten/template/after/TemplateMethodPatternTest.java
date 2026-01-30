package com.example.ch7.patten.template.after;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TemplateMethodPatternTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("CSV 프로세서가 템플릿 메서드의 흐름을 올바르게 따르는지 테스트")
    void testCsvDataProcessor() {
        // given
        AbstractDataProcessor processor = new CsvDataProcessor();

        // when
        processor.process();

        // then
        String output = outContent.toString();
        assertTrue(output.contains("[공통] 데이터베이스에서 데이터를 조회합니다."), "공통 조회 로직이 실행되지 않았습니다.");
        assertTrue(output.contains("[CSV] 데이터를 쉼표(,)로 구분된 형식으로 변환합니다."), "CSV 변환 로직이 실행되지 않았습니다.");
        assertTrue(output.contains("[저장] data.csv 파일이 성공적으로 생성되었습니다."), "CSV 파일 저장 로직이 실행되지 않았습니다.");
    }

    @Test
    @DisplayName("TXT 프로세서가 템플릿 메서드의 흐름을 올바르게 따르는지 테스트")
    void testTxtDataProcessor() {
        // given
        AbstractDataProcessor processor = new TxtDataProcessor();

        // when
        processor.process();

        // then
        String output = outContent.toString();
        assertTrue(output.contains("[공통] 데이터베이스에서 데이터를 조회합니다."), "공통 조회 로직이 실행되지 않았습니다.");
        assertTrue(output.contains("[TXT] 데이터를 공백( )으로 구분된 형식으로 변환합니다."), "TXT 변환 로직이 실행되지 않았습니다.");
        assertTrue(output.contains("[저장] data.txt 파일이 성공적으로 생성되었습니다."), "TXT 파일 저장 로직이 실행되지 않았습니다.");
    }
}
