package com.example.ch4.functions.app3;

/**
 * 도메인주도 개발.
 */
public class Main {
    public static void main(String[] args) {
        // 예시: 상수 값들은 외부 프로퍼티에서 불러온 것으로 가정 (여기서는 하드코딩)
        OrderProperties properties = new OrderProperties(0.1, 3000, 500, 0.01);

        // OrderService 생성
        OrderService orderService = new OrderService(properties);

        // Order 생성 (예: 단가 10,000, 수량 5)
        Order order = new Order(10000.0, 5);

        // 주문 처리
        Order processedOrder = orderService.processOrder(order);

        // 결과 출력
        System.out.println("[최종 주문 정보] " + processedOrder);
    }
}
