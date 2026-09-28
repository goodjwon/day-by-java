package dev.wonslab.practice.core.container;

import dev.wonslab.practice.core.event.InventoryListener;
import dev.wonslab.practice.core.event.ShippingListener;
import dev.wonslab.practice.core.order.OrderService;
import dev.wonslab.practice.core.order.ReceiptRepository;
import dev.wonslab.practice.core.payment.BankTransferPayment;
import dev.wonslab.practice.core.payment.CardPayment;

import java.util.List;

public final class AppConfig {

    private AppConfig() {
    }

    public static MiniContainer container() {
        MiniContainer c = new MiniContainer();
        c.register(ReceiptRepository.class, x -> new ReceiptRepository());
        c.register(InventoryListener.class, x -> new InventoryListener());
        c.register(ShippingListener.class, x -> new ShippingListener());
        c.register(OrderService.class, x -> new OrderService(
                List.of(new CardPayment(), new BankTransferPayment()),
                x.get(ReceiptRepository.class),
                List.of(x.get(InventoryListener.class), x.get(ShippingListener.class))));
        return c;
    }
}
