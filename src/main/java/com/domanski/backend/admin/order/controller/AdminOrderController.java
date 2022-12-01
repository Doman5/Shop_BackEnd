package com.domanski.backend.admin.order.controller;


import com.domanski.backend.admin.order.controller.mappper.AdminOrderMapper;
import com.domanski.backend.admin.order.model.AdminOrder;
import com.domanski.backend.admin.order.controller.dto.AdminOrderDto;
import com.domanski.backend.admin.order.service.AdminOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/orders")
@RequiredArgsConstructor
public class AdminOrderController {

    private final AdminOrderService adminOrderService;

    @GetMapping
    public Page<AdminOrderDto> getAllAdminOrders(Pageable pageable) {
        return AdminOrderMapper.mapToPageDtos(adminOrderService.getAllAdminOrders(pageable));
    }

    @GetMapping("/{id}")
    public AdminOrder getAdminOrder(@PathVariable Long id) {
        return adminOrderService.getAdminOrder(id);
    }
}
