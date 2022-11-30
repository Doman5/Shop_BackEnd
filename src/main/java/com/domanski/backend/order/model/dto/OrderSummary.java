package com.domanski.backend.order.model.dto;

import com.domanski.backend.order.model.OrderStatus;
import com.domanski.backend.order.model.Payment;
import lombok.Builder;
import lombok.Getter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class OrderSummary {
    private Long id;
    private LocalDateTime placeDate;
    private OrderStatus orderStatus;
    private BigDecimal grossValue;
    private Payment payment;
}
