package com.domanski.backend.admin.order.service;

import com.domanski.backend.admin.order.model.AdminOrderStatus;

public class AdminOrderEmailService {

    public static String createProcessingEmailMessage(Long id, AdminOrderStatus status) {
        return "Twoje zamównienie: " + id + "jest przetwarzane." +
                "\nStatus został zmieniony na " + status.getValue() +
                "\nTwoje zamówienie jest przetwarzane przez naszych pracowników" +
                "\nPo skompletowaniu niezwłocznie wyślemy je do wysyłki" +
                "\n\n Pozdrawiamy" +
                "\n Sklep Shop";
    }

    public static String createCompletedEmailMessage(Long id, AdminOrderStatus status) {
        return "Twoje zamówieni o id " + id + " zostało zrealizowane." +
                "\nStatus twojego zamówienia został zmieniony na: " + status.getValue() +
                "\n\n Dziękujemy za zakupy i zapraszamy ponownie" +
                "\n Sklep Shop";
    }

    public static String createRefundEmailMessage(Long id, AdminOrderStatus status) {
        return "Twoje zamówieni o id " + id + " zostało wrócone." +
                "\nStatus twojego zamówienia został zmieniony na: " + status.getValue() +
                "\n\n Pozdrawiamy" +
                "\n Sklep Shop";
    }
}
