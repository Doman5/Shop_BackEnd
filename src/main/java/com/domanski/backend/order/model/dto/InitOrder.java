package com.domanski.backend.order.model.dto;

import com.domanski.backend.order.model.Shipment;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class InitOrder {
    private List<Shipment> shipments;
}
