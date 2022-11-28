package com.domanski.backend.cart.service;

import com.domanski.backend.cart.model.Cart;
import com.domanski.backend.cart.repository.CartItemRepository;
import com.domanski.backend.cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartCleanUpService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Transactional
    @Scheduled(cron = "${app.cart.cleanup.expression}")
    public void cleanOldCarts() {
        List<Cart> carts = cartRepository.findByCreatedLessThan(LocalDateTime.now().minusDays(3));
        List<Long> ids = carts.stream()
                .map(Cart::getId)
                .toList();

        if(!ids.isEmpty()) {
            cartItemRepository.deleteAllByIdIn(ids);
            cartRepository.deleteCartByIdIn(ids);
        }
    }
}
