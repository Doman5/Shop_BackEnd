package com.domanski.backend.order.controller;

import com.domanski.backend.order.model.dto.InitOrder;
import com.domanski.backend.order.model.dto.OrderDto;
import com.domanski.backend.order.model.dto.OrderListDto;
import com.domanski.backend.order.model.dto.OrderSummary;
import com.domanski.backend.order.service.OrderService;
import com.domanski.backend.order.service.PaymentService;
import com.domanski.backend.order.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final ShipmentService shipmentService;
    private final PaymentService paymentService;

    @PostMapping
    public OrderSummary placeOrder(@RequestBody OrderDto orderDto, @AuthenticationPrincipal Long userId) {
        return orderService.placeOrder(orderDto, userId);
    }

    @GetMapping("/initData")
    public InitOrder initData() {
        return InitOrder.builder()
                .shipments(shipmentService.getShipments())
                .payments(paymentService.getPayments())
                .build();
    }

    @GetMapping
    public List<OrderListDto> getOrders(@AuthenticationPrincipal Long userId) {
        if(userId != null) {
            return orderService.getOrders(userId);
        } else {
            throw new IllegalArgumentException("Brak użytkownika");
        }
    }
}
