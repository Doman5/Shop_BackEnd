package com.domanski.backend.admin.order.controller.dto;

import com.domanski.backend.admin.order.model.AdminOrderStatus;
import com.domanski.backend.admin.order.model.AdminPayment;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class AdminOrderDto {
    private Long id;
    private LocalDateTime placeDate;
    private AdminOrderStatus orderStatus;
    private BigDecimal grossValue;
    private String firstname;
    private String lastname;
    private String street;
    private String zipcode;
    private String city;
    private String email;
    private String phone;
    private AdminPayment payment;
}
