package dev.wonslab.practice.core.order;

import dev.wonslab.practice.core.event.OrderListener;
import dev.wonslab.practice.core.payment.PaymentStrategy;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OrderService {

    private final Queue<Order> pending = new ArrayDeque<>();
    private final Map<String, PaymentStrategy> strategies;
    private final ReceiptRepository repository;
    private final List<OrderListener> listeners;

    public OrderService(List<PaymentStrategy> strategies, ReceiptRepository repository,
                        List<OrderListener> listeners) {
        this.strategies = strategies.stream()
                .collect(Collectors.toMap(PaymentStrategy::name, Function.identity()));
        this.repository = repository;
        this.listeners = List.copyOf(listeners);
    }

    public void place(Order order) {
        if (!strategies.containsKey(order.payMethod())) {
            throw new IllegalArgumentException("지원하지 않는 결제 수단입니다: " + order.payMethod());
        }
        pending.offer(order);
    }

    public int pendingCount() {
        return pending.size();
    }

    public List<Receipt> processAll() {
        List<Receipt> done = new ArrayList<>();
        Order order;
        while ((order = pending.poll()) != null) {
            PaymentStrategy strategy = strategies.get(order.payMethod());
            Receipt receipt = new Receipt(order, strategy.name(), strategy.fee(order.amount()));
            System.out.printf("[결제] %s %s %,d원 (수수료 %,d원)%n",
                    order.id(), strategy.name(), receipt.total(), receipt.fee());
            repository.save(receipt);
            listeners.forEach(listener -> listener.onPaid(receipt));
            done.add(receipt);
        }
        return done;
    }
}
