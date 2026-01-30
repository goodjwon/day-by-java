package com.example.ch7.patten.observer.after;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ContextConfiguration(classes = {OrderService.class, InventoryService.class, ShippingService.class, CouponService.class})
class ObserverPatternTest {

    @Autowired
    private OrderService orderService;

    @SpyBean
    private InventoryService inventoryService;

    @SpyBean
    private ShippingService shippingService;

    @SpyBean
    private CouponService couponService;

    @Test
    @DisplayName("주문 시 재고, 배송, 쿠폰 서비스에 이벤트가 전달되는지 테스트")
    void testPlaceOrderNotifiesListeners() {
        // given
        String productId = "product-123";
        String address = "123 Main St";
        String orderId = "order-456";

        // when
        orderService.placeOrder(productId, address, orderId);

        // then
        // 각 리스너의 onOrderPlaced 메서드가 한 번씩 호출되었는지 확인
        verify(inventoryService, times(1)).onOrderPlaced(any(OrderPlacedEvent.class));
        verify(shippingService, times(1)).onOrderPlaced(any(OrderPlacedEvent.class));
        verify(couponService, times(1)).onOrderPlaced(any(OrderPlacedEvent.class));
    }
}
