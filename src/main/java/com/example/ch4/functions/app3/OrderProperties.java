package com.example.ch4.functions.app3;

// 실제 환경에서는 프로퍼티 파일이나 설정 서버에서 값을 읽어올 수 있습니다.
public class OrderProperties {
    private final double discountRate;
    private final int baseShippingFee;
    private final int perItemShippingFee;
    private final double pointRate;

    public OrderProperties(double discountRate, int baseShippingFee, int perItemShippingFee, double pointRate) {
        this.discountRate = discountRate;
        this.baseShippingFee = baseShippingFee;
        this.perItemShippingFee = perItemShippingFee;
        this.pointRate = pointRate;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public int getBaseShippingFee() {
        return baseShippingFee;
    }

    public int getPerItemShippingFee() {
        return perItemShippingFee;
    }

    public double getPointRate() {
        return pointRate;
    }
}
