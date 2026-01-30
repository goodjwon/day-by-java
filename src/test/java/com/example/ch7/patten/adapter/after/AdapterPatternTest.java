package com.example.ch7.patten.adapter.after;

import com.example.ch7.patten.adapter.before.NewJsonLibrary;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AdapterPatternTest {

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
    @DisplayName("JSON 어댑터가 새로운 JSON 라이브러리를 올바르게 호출하는지 테스트")
    void testJsonAdapter() {
        // given
        NewJsonLibrary newJsonLibrary = new NewJsonLibrary();
        String jsonData = "{\"data\":\"test\"}";
        JsonAdapter jsonAdapter = new JsonAdapter(newJsonLibrary, jsonData);
        DataService dataService = new DataService();

        // when
        dataService.process(jsonAdapter);

        // then
        String output = outContent.toString();
        assertTrue(output.contains("🔌 [어댑터 작동]"), "어댑터의 작동 메시지가 출력되지 않았습니다.");
        assertTrue(output.contains("새로운 라이브러리로 JSON 데이터를 처리합니다: " + jsonData),
                "새로운 JSON 라이브러리의 처리 메시지가 출력되지 않았습니다.");
    }
}
