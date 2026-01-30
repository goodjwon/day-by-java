package com.example.ch7.patten.proxy.after;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ProxyPatternTest {

    @Configuration
    @EnableAspectJAutoProxy
    @ComponentScan(basePackages = "com.example.ch7.patten.proxy.after")
    static class TestConfig {
    }


    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Autowired
    private EventService eventService;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("AOP 프록시가 EventService의 성능을 측정하는지 테스트")
    void testPerformanceAspect() {
        // when
        eventService.processEvent("Test Event");

        // then
        String output = outContent.toString();
        assertTrue(output.contains("✨ [AOP 프록시]"), "AOP 프록시의 성능 측정 메시지가 출력되지 않았습니다.");
        assertTrue(output.contains("실행 시간:"), "실행 시간이 출력되지 않았습니다.");
        assertTrue(output.contains("이벤트 처리 시작: Test Event"), "원본 서비스의 메시지가 출력되지 않았습니다.");
    }
}
