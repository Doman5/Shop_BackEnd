package com.domanski.backend.cart.controller.mapper;

import com.domanski.backend.cart.controller.dto.CartSummaryDto;
import com.domanski.backend.cart.controller.dto.CartSummaryItemDto;
import com.domanski.backend.cart.controller.dto.ProductDto;
import com.domanski.backend.cart.controller.dto.SummaryDto;
import com.domanski.backend.common.model.Cart;
import com.domanski.backend.common.model.CartItem;
import com.domanski.backend.common.model.Product;

import java.math.BigDecimal;
import java.util.List;

public class CartMapper {
    public static CartSummaryDto mapToCartSummary(Cart cart) {
        return CartSummaryDto.builder()
                .id(cart.getId())
                .items(mapCartItems(cart.getCartItems()))
                .summary(mapToSummary(cart.getCartItems()))
                .build();
    }

    private static List<CartSummaryItemDto> mapCartItems(List<CartItem> cartItems) {
        return cartItems.stream()
                .map(CartMapper::mapToCartItem)
                .toList();
    }

    private static CartSummaryItemDto mapToCartItem(CartItem cartItem) {
        return CartSummaryItemDto.builder()
                .id(cartItem.getId())
                .quantity(cartItem.getQuantity())
                .product(mapToProductDto(cartItem.getProduct()))
                .lineValue(calculateLineValue(cartItem))
                .build();
    }

    private static ProductDto mapToProductDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getEndPrice())
                .currency(product.getCurrency())
                .image(product.getImage())
                .slug(product.getSlug())
                .build();
    }

    private static BigDecimal calculateLineValue(CartItem cartItem) {
        return cartItem.getProduct().getEndPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()));
    }

    private static SummaryDto mapToSummary(List<CartItem> cartItems) {
        return SummaryDto.builder()
                .grossValue(sumValues(cartItems))
                .build();
    }

    private static BigDecimal sumValues(List<CartItem> cartItems) {
        return cartItems.stream()
                .map(CartMapper::calculateLineValue)
                .reduce((BigDecimal::add))
                .orElse(BigDecimal.ZERO);
    }
}



