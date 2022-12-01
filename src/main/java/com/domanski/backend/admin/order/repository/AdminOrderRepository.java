package com.domanski.backend.admin.order.repository;

import com.domanski.backend.admin.order.model.AdminOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminOrderRepository extends JpaRepository<AdminOrder, Long> {
}
