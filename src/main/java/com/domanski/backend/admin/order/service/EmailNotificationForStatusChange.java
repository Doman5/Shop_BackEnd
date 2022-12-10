package com.domanski.backend.admin.order.service;

import com.domanski.backend.admin.order.model.AdminOrder;
import com.domanski.backend.common.mail.EmailClientService;
import com.domanski.backend.common.model.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.domanski.backend.admin.order.service.AdminOrderEmailService.createCompletedEmailMessage;
import static com.domanski.backend.admin.order.service.AdminOrderEmailService.createProcessingEmailMessage;
import static com.domanski.backend.admin.order.service.AdminOrderEmailService.createRefundEmailMessage;

@Service
@RequiredArgsConstructor
class EmailNotificationForStatusChange {

    private final EmailClientService emailClientService;

    public void sendEmailNotification(OrderStatus newStatus, AdminOrder order) {
        if(newStatus == OrderStatus.PROCESSING) {
            sendEmail(order.getEmail(), "Zamówienie " + order.getId() + " zmienioło status na " + newStatus.getValue(),
                    createProcessingEmailMessage(order.getId(), newStatus));
        } else if (newStatus == OrderStatus.COMPLETED) {
            sendEmail(order.getEmail(), "Zamówienie " + order.getId() + " zostało zrealizowane",
                    createCompletedEmailMessage(order.getId(), newStatus));
        } else if (newStatus == OrderStatus.REFUND) {
            sendEmail(order.getEmail(), "Zamówienie " + order.getId() + " zostało zwrócone",
                    createRefundEmailMessage(order.getId(), newStatus));
        }
    }

    private void sendEmail(String email, String subject, String message) {
        emailClientService.getInstance().send(email, subject, message);
    }

}
