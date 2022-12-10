package com.domanski.backend.order.service.mapper;

import com.domanski.backend.order.model.Order;
import com.domanski.backend.order.model.dto.OrderListDto;

public class OrderDtoMapper {

    public static OrderListDto mapToOrderListDto(Order order) {
        return OrderListDto.builder()
                .id(order.getId())
                .orderStatus(order.getOrderStatus())
                .placeDate(order.getPlaceDate())
                .grossValue(order.getGrossValue())
                .build();
    }
}
