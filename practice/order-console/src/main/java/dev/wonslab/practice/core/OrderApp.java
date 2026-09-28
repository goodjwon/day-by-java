package dev.wonslab.practice.core;

import dev.wonslab.practice.core.container.AppConfig;
import dev.wonslab.practice.core.container.MiniContainer;
import dev.wonslab.practice.core.order.Order;
import dev.wonslab.practice.core.order.OrderService;
import dev.wonslab.practice.core.order.ReceiptRepository;

public class OrderApp {

    public static void main(String[] args) {
        MiniContainer container = AppConfig.container();
        OrderService service = container.get(OrderService.class);
        ReceiptRepository repository = container.get(ReceiptRepository.class);

        service.place(new Order("O-001", "김자바", "노트북", 1, 1_200_000, "CARD"));
        service.place(new Order("O-002", "이스트림", "마우스", 2, 25_000, "BANK"));
        service.place(new Order("O-003", "김자바", "마우스", 1, 25_000, "CARD"));
        try {
            service.place(new Order("O-004", "박큐", "키보드", 1, 80_000, "POINT"));
        } catch (IllegalArgumentException e) {
            System.out.println("[거절] O-004 " + e.getMessage());
        }
        System.out.println("대기 주문: " + service.pendingCount() + "건");

        service.processAll();

        System.out.println("---- 정산 ----");
        System.out.printf("총 매출: %,d원%n", repository.totalSales());
        repository.salesByCustomer()
                .forEach((customer, sum) -> System.out.printf("%s: %,d원%n", customer, sum));
        System.out.println("인기 상품: " + repository.topItems(2));
    }
}
