package dev.wonslab.practice.core.payment;

public class CardPayment implements PaymentStrategy {

    @Override
    public String name() {
        return "CARD";
    }

    @Override
    public long fee(long amount) {
        return amount * 3 / 100;
    }
}
