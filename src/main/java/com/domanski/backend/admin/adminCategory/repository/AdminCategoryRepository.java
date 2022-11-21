package com.domanski.backend.admin.adminCategory.repository;

import com.domanski.backend.admin.adminCategory.model.AdminCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminCategoryRepository extends JpaRepository<AdminCategory, Long> {

}
