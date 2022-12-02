package com.domanski.backend.admin.order.model;

public enum AdminOrderStatus {
    NEW("nowe"),
    PAID("opłacone"),
    PROCESSING("Przetwarzane"),
    WAITING_FOR_DELIVERY("Czeka na dostawe"),
    COMPLETED("Zrealizowane"),
    CANCELED("Anulowane"),
    REFUND("Zwrócone");

    private final String value;

    AdminOrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
