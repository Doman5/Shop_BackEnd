package com.domanski.backend.order.repository;

import com.domanski.backend.order.model.OrderRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRowsRepository extends JpaRepository<OrderRow, Long> {
}
