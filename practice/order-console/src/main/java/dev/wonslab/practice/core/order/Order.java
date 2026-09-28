package dev.wonslab.practice.core.order;

public record Order(String id, String customer, String item,
                    int quantity, long unitPrice, String payMethod) {

    public Order {
        if (quantity <= 0) {
            throw new IllegalArgumentException("수량은 1 이상이어야 합니다: " + quantity);
        }
        if (unitPrice <= 0) {
            throw new IllegalArgumentException("단가는 0보다 커야 합니다: " + unitPrice);
        }
    }

    public long amount() {
        return unitPrice * quantity;
    }
}
