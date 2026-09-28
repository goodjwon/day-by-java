package dev.wonslab.practice.core.payment;

public interface PaymentStrategy {

    String name();

    long fee(long amount);
}
