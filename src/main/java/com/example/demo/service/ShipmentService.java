package com.example.demo.service;

import com.example.demo.dto.ShipmentDto;
import com.example.demo.entity.Shipment;

import java.util.Optional;

public interface ShipmentService {
    Shipment createShipment(ShipmentDto shipmentDto);
    Optional<Shipment> getShipment(Long shipmentId);
}
