package com.domanski.backend.order.service;

import com.domanski.backend.common.mail.EmailClientService;
import com.domanski.backend.common.model.Cart;
import com.domanski.backend.common.repository.CartItemRepository;
import com.domanski.backend.common.repository.CartRepository;
import com.domanski.backend.order.model.Order;
import com.domanski.backend.order.model.Payment;
import com.domanski.backend.order.model.Shipment;
import com.domanski.backend.order.model.dto.OrderDto;
import com.domanski.backend.order.model.dto.OrderSummary;
import com.domanski.backend.order.model.dto.OrderListDto;
import com.domanski.backend.order.repository.OrderRepository;
import com.domanski.backend.order.repository.OrderRowsRepository;
import com.domanski.backend.order.repository.PaymentRepository;
import com.domanski.backend.order.repository.ShipmentRepository;
import com.domanski.backend.order.service.mapper.OrderDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.domanski.backend.order.service.mapper.OrderEmailMessageMapper.createEmailMessage;
import static com.domanski.backend.order.service.mapper.OrderMapper.createNewOrder;
import static com.domanski.backend.order.service.mapper.OrderMapper.createOrderSummary;
import static com.domanski.backend.order.service.mapper.OrderMapper.mapToOrderRow;
import static com.domanski.backend.order.service.mapper.OrderMapper.mapToOrderRowWithQuantity;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final OrderRowsRepository orderRowsRepository;
    private final CartItemRepository cartItemRepository;
    private final ShipmentRepository shipmentRepository;
    private final PaymentRepository paymentRepository;
//    Search mail host and set it in config because gmail don't support unsafe apps(work only FakeEmailService)
    private final EmailClientService emailClientService;

    @Transactional
    public OrderSummary placeOrder(OrderDto orderDto, Long userId) {
        Cart cart = cartRepository.findById(orderDto.getCartId()).orElseThrow();
        Shipment shipment = shipmentRepository.findById(orderDto.getShipmentId()).orElseThrow();
        Payment payment = paymentRepository.findById(orderDto.getPaymentId()).orElseThrow();
        Order newOrder = orderRepository.save(createNewOrder(orderDto, cart, shipment, payment, userId));
        saveOrderRows(cart, newOrder.getId(), shipment);
        clearOrderCart(orderDto);
        sendConfirmEmail(newOrder);
        return createOrderSummary(newOrder);
    }

    private void sendConfirmEmail(Order newOrder) {
        emailClientService.getInstance()
                .send(newOrder.getEmail(), "Twoje zamówienie zostało przyjęte",
                        createEmailMessage(newOrder));
    }

    private void clearOrderCart(OrderDto orderDto) {
        cartItemRepository.deleteByCartId(orderDto.getCartId());
        cartRepository.deleteById(orderDto.getCartId());
    }

    private void saveOrderRows(Cart cart, Long orderId, Shipment shipment) {
        saveProductRows(cart, orderId);
        saveShipmentRow(orderId, shipment);
    }

    private void saveShipmentRow(Long orderId, Shipment shipment) {
        orderRowsRepository.save(mapToOrderRow(orderId, shipment));
    }

    private void saveProductRows(Cart cart, Long orderId) {
        cart.getCartItems().stream()
                .map(cartItem -> mapToOrderRowWithQuantity(orderId, cartItem))
                .peek(orderRowsRepository::save)
                .toList();
    }

    public List<OrderListDto> getOrders(Long userId) {
        return orderRepository.findByUserId(userId).stream()
                .map(OrderDtoMapper::mapToOrderListDto)
                .toList();
    }


}
