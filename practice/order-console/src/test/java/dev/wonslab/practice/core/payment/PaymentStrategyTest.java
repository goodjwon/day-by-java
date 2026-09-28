package dev.wonslab.practice.core.payment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentStrategyTest {

    @Test
    @DisplayName("카드 수수료는 결제 금액의 3%다")
    void cardFee() {
        assertEquals(3_000, new CardPayment().fee(100_000));
    }

    @Test
    @DisplayName("계좌이체 수수료는 금액과 무관하게 500원이다")
    void bankFee() {
        assertEquals(500, new BankTransferPayment().fee(100_000));
        assertEquals(500, new BankTransferPayment().fee(1_000));
    }

    @Test
    @DisplayName("같은 인터페이스로 여러 전략을 다룬다 (다형성)")
    void polymorphism() {
        List<PaymentStrategy> strategies = List.of(new CardPayment(), new BankTransferPayment());
        long totalFee = strategies.stream().mapToLong(s -> s.fee(10_000)).sum();
        assertEquals(800, totalFee);
    }
}
