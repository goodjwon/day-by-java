package dev.wonslab.practice.core.event;

import dev.wonslab.practice.core.order.Order;
import dev.wonslab.practice.core.order.Receipt;

import java.util.HashMap;
import java.util.Map;

public class InventoryListener implements OrderListener {

    private final Map<String, Integer> sold = new HashMap<>();

    @Override
    public void onPaid(Receipt receipt) {
        Order order = receipt.order();
        int total = sold.merge(order.item(), order.quantity(), Integer::sum);
        System.out.printf("  [재고] %s %d개 차감 (누적 %d개)%n", order.item(), order.quantity(), total);
    }

    public int soldCount(String item) {
        return sold.getOrDefault(item, 0);
    }
}
