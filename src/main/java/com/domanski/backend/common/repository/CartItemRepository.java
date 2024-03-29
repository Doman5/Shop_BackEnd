package com.domanski.backend.common.repository;

import com.domanski.backend.common.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    Long countByCartId(Long cartId);

    @Query("delete from CartItem ci where ci.cartId in (:ids)")
    @Modifying
    void deleteAllByIdIn(List<Long> ids);

    void deleteByCartId(Long cartId);
}
