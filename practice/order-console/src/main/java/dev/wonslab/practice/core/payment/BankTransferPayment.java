package dev.wonslab.practice.core.payment;

public class BankTransferPayment implements PaymentStrategy {

    @Override
    public String name() {
        return "BANK";
    }

    @Override
    public long fee(long amount) {
        return 500;
    }
}
