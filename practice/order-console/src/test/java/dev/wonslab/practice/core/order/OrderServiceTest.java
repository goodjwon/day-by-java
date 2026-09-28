package dev.wonslab.practice.core.order;

import dev.wonslab.practice.core.event.OrderListener;
import dev.wonslab.practice.core.payment.BankTransferPayment;
import dev.wonslab.practice.core.payment.CardPayment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderServiceTest {

    private final ReceiptRepository repository = new ReceiptRepository();
    private final List<String> events = new ArrayList<>();
    private final OrderListener recorder = receipt -> events.add(receipt.order().id());
    private final OrderService service = new OrderService(
            List.of(new CardPayment(), new BankTransferPayment()), repository, List.of(recorder));

    @Test
    @DisplayName("대기 주문은 들어온 순서(FIFO)대로 처리된다")
    void processInOrder() {
        service.place(new Order("A", "김자바", "펜", 1, 1_000, "BANK"));
        service.place(new Order("B", "김자바", "펜", 1, 1_000, "CARD"));

        List<Receipt> receipts = service.processAll();

        assertEquals(List.of("A", "B"), receipts.stream().map(r -> r.order().id()).toList());
        assertEquals(0, service.pendingCount());
    }

    @Test
    @DisplayName("결제가 끝나면 등록된 리스너가 모두 알림을 받는다")
    void notifyListeners() {
        service.place(new Order("A", "김자바", "펜", 1, 1_000, "CARD"));
        service.processAll();

        assertEquals(List.of("A"), events);
        assertEquals(1, repository.findAll().size());
    }

    @Test
    @DisplayName("지원하지 않는 결제 수단은 대기열에 들어가지 않는다")
    void rejectUnknownPayMethod() {
        assertThrows(IllegalArgumentException.class,
                () -> service.place(new Order("A", "김자바", "펜", 1, 1_000, "POINT")));
        assertEquals(0, service.pendingCount());
    }
}
