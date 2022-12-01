package com.domanski.backend.cart.service;

import com.domanski.backend.common.model.Cart;
import com.domanski.backend.common.model.CartItem;
import com.domanski.backend.cart.dto.CartProductDto;
import com.domanski.backend.common.repository.CartRepository;
import com.domanski.backend.common.model.Product;
import com.domanski.backend.common.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public Cart getCart(Long id) {
        return cartRepository.findById(id).orElseThrow();
    }

    @Transactional
    public Cart addProductToCart(Long id, CartProductDto cartProductDto) {
        Cart cart = getInitializedCart(id);
        cart.addProduct(CartItem.builder()
                        .quantity(cartProductDto.quantity())
                        .product(getProduct(cartProductDto.productId()))
                        .cartId(cart.getId())
                .build());
        return cart;
    }

    private Product getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow();
    }

    private Cart getInitializedCart(Long id) {
        if(id == null || id <= 0) {
            return cartRepository.save(Cart.builder().created(now()).build());
        }
        return cartRepository.findById(id).orElseGet(this::saveNewCart);
    }

    private Cart saveNewCart() {
        return cartRepository.save(Cart.builder().created(now()).build());
    }

    @Transactional
    public Cart updateCart(Long id, List<CartProductDto> cartProductDtos) {
        Cart cart = cartRepository.findById(id).orElseThrow();
        cart.getCartItems().forEach(cartItem -> cartProductDtos.stream()
                .filter(cartProductDto -> cartItem.getProduct().getId().equals(cartProductDto.productId()))
                .findFirst()
                .ifPresent(cartProductDto -> cartItem.setQuantity(cartProductDto.quantity())));
        return cart;
    }
}
