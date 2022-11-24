package com.domanski.backend.admin.review.repository;

import com.domanski.backend.admin.review.model.AdminReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminReviewRepository extends JpaRepository<AdminReview, Long> {

    @Query("update AdminReview r set r.moderated=true where r.id=:id")
    @Modifying
    void moderate(Long id);

    List<AdminReview> findAllByModeratedFalse();
}
