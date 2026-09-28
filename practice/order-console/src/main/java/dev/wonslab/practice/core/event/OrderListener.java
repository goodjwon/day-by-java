package dev.wonslab.practice.core.event;

import dev.wonslab.practice.core.order.Receipt;

@FunctionalInterface
public interface OrderListener {

    void onPaid(Receipt receipt);
}
