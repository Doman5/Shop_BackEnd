package com.domanski.backend.admin.adminProduct.repository;

import com.domanski.backend.admin.adminProduct.model.AdminProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminProductRepository extends JpaRepository<AdminProduct, Long> {
}
