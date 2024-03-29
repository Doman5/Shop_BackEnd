package com.domanski.backend.admin.order.model;

import com.domanski.backend.admin.product.model.AdminProduct;
import com.domanski.backend.order.model.Shipment;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "order_row")
@Getter
public class AdminOrderRow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;
    @OneToOne
    @JoinColumn(name = "productId")
    private AdminProduct product;
    private int quantity;
    private BigDecimal price;
    @OneToOne
    @JoinColumn(name = "shipmentId")
    private Shipment shipment;
}
