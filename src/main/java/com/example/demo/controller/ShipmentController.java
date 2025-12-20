package com.example.demo.controller;

import com.example.demo.dto.ShipmentDto;
import com.example.demo.entity.Shipment;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.ShipmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shipments")
public class ShipmentController {

    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @PostMapping("/{vehicleId}")
    public ResponseEntity<Shipment> createShipment(@PathVariable Long vehicleId, @RequestBody ShipmentDto shipmentDto) {
        shipmentDto.setVehicleId(vehicleId);
        Shipment newShipment = shipmentService.createShipment(shipmentDto);
        return ResponseEntity.ok(newShipment);
    }

    @GetMapping("/{shipmentId}")
    public ResponseEntity<Shipment> getShipment(@PathVariable Long shipmentId) {
        Shipment shipment = shipmentService.getShipment(shipmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment not found with id: " + shipmentId));
        return ResponseEntity.ok(shipment);
    }
}
