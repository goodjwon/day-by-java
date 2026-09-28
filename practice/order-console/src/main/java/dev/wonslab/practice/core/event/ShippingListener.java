package dev.wonslab.practice.core.event;

import dev.wonslab.practice.core.order.Receipt;

import java.util.ArrayList;
import java.util.List;

public class ShippingListener implements OrderListener {

    private final List<String> shipped = new ArrayList<>();

    @Override
    public void onPaid(Receipt receipt) {
        shipped.add(receipt.order().id());
        System.out.printf("  [배송] %s -> %s 님 배송 준비%n", receipt.order().id(), receipt.order().customer());
    }

    public List<String> shippedOrderIds() {
        return List.copyOf(shipped);
    }
}
