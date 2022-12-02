package com.domanski.backend.admin.order.service;

import com.domanski.backend.admin.order.model.AdminOrder;
import com.domanski.backend.admin.order.model.AdminOrderStatus;
import com.domanski.backend.admin.order.repository.AdminOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminOrderService {

    private final AdminOrderRepository adminOrderRepository;


    public Page<AdminOrder> getAllAdminOrders(Pageable pageable) {
        return adminOrderRepository.findAll(
                PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        Sort.by("id").descending())
        );
    }

    public AdminOrder getAdminOrder(Long id) {
        return adminOrderRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void patchOrder(Long id, Map<String, String> values) {
        AdminOrder order = adminOrderRepository.findById(id).orElseThrow();
        patchValues(order, values);
    }

    private void patchValues(AdminOrder order, Map<String, String> values) {
        if(values.get("orderStatus") != null) {
            order.setOrderStatus(AdminOrderStatus.valueOf(values.get("orderStatus")));
        }
    }
}
