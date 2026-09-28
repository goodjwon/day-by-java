package dev.wonslab.practice.core.container;

import dev.wonslab.practice.core.event.InventoryListener;
import dev.wonslab.practice.core.order.Order;
import dev.wonslab.practice.core.order.OrderService;
import dev.wonslab.practice.core.order.ReceiptRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MiniContainerTest {

    @Test
    @DisplayName("같은 타입을 두 번 꺼내면 같은 인스턴스(싱글톤)다")
    void singleton() {
        MiniContainer container = AppConfig.container();
        assertSame(container.get(ReceiptRepository.class), container.get(ReceiptRepository.class));
    }

    @Test
    @DisplayName("등록하지 않은 타입을 꺼내면 예외가 난다")
    void unknownType() {
        MiniContainer container = new MiniContainer();
        assertThrows(IllegalStateException.class, () -> container.get(String.class));
    }

    @Test
    @DisplayName("컨테이너가 주입한 의존 객체를 서비스와 리스너가 공유한다")
    void wiring() {
        MiniContainer container = AppConfig.container();
        OrderService service = container.get(OrderService.class);

        service.place(new Order("A", "김자바", "펜", 2, 1_000, "CARD"));
        service.processAll();

        assertEquals(1, container.get(ReceiptRepository.class).findAll().size());
        assertEquals(2, container.get(InventoryListener.class).soldCount("펜"));
    }
}
