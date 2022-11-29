package com.domanski.backend.cart.service;

import com.domanski.backend.common.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    public void deleteCartItem(Long id) {
        cartItemRepository.deleteById(id);
    }

    public Long countItemsInCart(Long cartId) {
        return cartItemRepository.countByCartId(cartId);
    }
}
