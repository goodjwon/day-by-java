package com.example.ch7.patten.strategy.after;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PaymentStrategyTest {

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
    @DisplayName("신용카드 결제 전략 테스트")
    void testCreditCardPayment() {
        // given
        PaymentStrategy strategy = new CreditCardStrategy();
        PaymentContext context = new PaymentContext();
        int amount = 10000;
        String expectedMessage = "신용카드로 " + amount + "원 결제가 완료되었습니다.";

        // when
        context.processPayment(strategy, amount);

        // then
        assertTrue(outContent.toString().contains(expectedMessage),
                "출력 메시지가 예상과 다릅니다. 실제 출력: " + outContent.toString());
    }

    @Test
    @DisplayName("계좌이체 결제 전략 테스트")
    void testBankTransferPayment() {
        // given
        PaymentStrategy strategy = new BankTransferStrategy();
        PaymentContext context = new PaymentContext();
        int amount = 25000;
        String expectedMessage = "계좌이체로 " + amount + "원 결제가 완료되었습니다.";

        // when
        context.processPayment(strategy, amount);

        // then
        assertTrue(outContent.toString().contains(expectedMessage),
                "출력 메시지가 예상과 다릅니다. 실제 출력: " + outContent.toString());
    }
}
