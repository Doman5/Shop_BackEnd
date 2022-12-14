package com.domanski.backend.order.service.mapper;

import com.domanski.backend.common.model.Cart;
import com.domanski.backend.common.model.CartItem;
import com.domanski.backend.common.model.OrderStatus;
import com.domanski.backend.order.model.Order;
import com.domanski.backend.order.model.OrderRow;
import com.domanski.backend.order.model.Payment;
import com.domanski.backend.order.model.Shipment;
import com.domanski.backend.order.model.dto.OrderDto;
import com.domanski.backend.order.model.dto.OrderSummary;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderMapper {
    public static Order createNewOrder(OrderDto orderDto, Cart cart, Shipment shipment, Payment payment, Long userId) {
        return Order.builder()
                .firstname(orderDto.getFirstname())
                .lastname(orderDto.getLastname())
                .street(orderDto.getStreet())
                .zipcode(orderDto.getZipcode())
                .city(orderDto.getCity())
                .email(orderDto.getEmail())
                .phone(orderDto.getPhone())
                .placeDate(LocalDateTime.now())
                .orderStatus(OrderStatus.NEW)
                .grossValue(calculateGrossValue(cart.getCartItems(), shipment))
                .payment(payment)
                .userId(userId)
                .build();
    }

    public static OrderSummary createOrderSummary(Order order) {
        return OrderSummary.builder()
                .id(order.getId())
                .placeDate(order.getPlaceDate())
                .orderStatus(order.getOrderStatus())
                .grossValue(order.getGrossValue())
                .payment(order.getPayment())
                .build();
    }

    public static OrderRow mapToOrderRow(Long orderId, Shipment shipment) {
        return OrderRow.builder()
                .quantity(1)
                .price(shipment.getPrice())
                .shipmentId(shipment.getId())
                .orderId(orderId)
                .build();
    }

    public static OrderRow mapToOrderRowWithQuantity(Long orderId, CartItem cartItem) {
        return OrderRow.builder()
                .quantity(cartItem.getQuantity())
                .productId(cartItem.getProduct().getId())
                .price(cartItem.getProduct().getEndPrice())
                .orderId(orderId)
                .build();
    }

    private static BigDecimal calculateGrossValue(List<CartItem> cartItems, Shipment shipment) {
        return cartItems.stream()
                .map(cartItem -> cartItem.getProduct().getEndPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO)
                .add(shipment.getPrice());
    }

}
