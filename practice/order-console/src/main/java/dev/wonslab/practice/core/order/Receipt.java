package dev.wonslab.practice.core.order;

public record Receipt(Order order, String method, long fee) {

    public long total() {
        return order.amount() + fee;
    }
}
