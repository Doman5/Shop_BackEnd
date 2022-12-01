package com.domanski.backend.admin.order.controller.mappper;

import com.domanski.backend.admin.order.model.AdminOrder;
import com.domanski.backend.admin.order.controller.dto.AdminOrderDto;
import org.springframework.data.domain.Page;

public class AdminOrderMapper {


    public static Page<AdminOrderDto> mapToPageDtos(Page<AdminOrder> allAdminOrders) {
        return allAdminOrders
                .map(adminOrder -> AdminOrderDto.builder()
                        .id(adminOrder.getId())
                        .placeDate(adminOrder.getPlaceDate())
                        .orderStatus(adminOrder.getOrderStatus())
                        .grossValue(adminOrder.getGrossValue())
                        .firstname(adminOrder.getFirstname())
                        .lastname(adminOrder.getLastname())
                        .street(adminOrder.getStreet())
                        .zipcode(adminOrder.getZipcode())
                        .city(adminOrder.getCity())
                        .email(adminOrder.getEmail())
                        .phone(adminOrder.getPhone())
                        .payment(adminOrder.getPayment())
                        .build());
    }
}
