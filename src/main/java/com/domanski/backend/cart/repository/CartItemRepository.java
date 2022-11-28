package com.domanski.backend.cart.repository;

import com.domanski.backend.cart.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    Long countByCartId(Long cartId);
}
