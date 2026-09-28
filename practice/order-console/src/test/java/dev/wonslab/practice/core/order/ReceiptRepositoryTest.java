package dev.wonslab.practice.core.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReceiptRepositoryTest {

    private final ReceiptRepository repository = new ReceiptRepository();

    @BeforeEach
    void setUp() {
        repository.save(new Receipt(new Order("O-1", "김자바", "노트북", 1, 1_000_000, "CARD"), "CARD", 30_000));
        repository.save(new Receipt(new Order("O-2", "이스트림", "마우스", 3, 20_000, "BANK"), "BANK", 500));
        repository.save(new Receipt(new Order("O-3", "김자바", "마우스", 1, 20_000, "BANK"), "BANK", 500));
    }

    @Test
    @DisplayName("총 매출은 모든 영수증 합계(상품 금액 + 수수료)다")
    void totalSales() {
        assertEquals(1_111_000, repository.totalSales());
    }

    @Test
    @DisplayName("고객별 매출은 이름 순으로 합산된다")
    void salesByCustomer() {
        assertEquals(Map.of("김자바", 1_050_500L, "이스트림", 60_500L), repository.salesByCustomer());
        assertEquals(List.of("김자바", "이스트림"), List.copyOf(repository.salesByCustomer().keySet()));
    }

    @Test
    @DisplayName("인기 상품은 판매 수량 내림차순이다")
    void topItems() {
        assertEquals(List.of("마우스", "노트북"), repository.topItems(2));
        assertEquals(List.of("마우스"), repository.topItems(1));
    }

    @Test
    @DisplayName("수량이 0인 주문은 만들 수 없다")
    void rejectZeroQuantity() {
        assertThrows(IllegalArgumentException.class,
                () -> new Order("O-9", "김자바", "노트북", 0, 1_000, "CARD"));
    }
}
