package com.example.demo.service.impl;

import com.example.demo.dto.ShipmentDto;
import com.example.demo.entity.Location;
import com.example.demo.entity.Shipment;
import com.example.demo.entity.Vehicle;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.LocationRepository;
import com.example.demo.repository.ShipmentRepository;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.service.ShipmentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final VehicleRepository vehicleRepository;
    private final LocationRepository locationRepository;

    public ShipmentServiceImpl(ShipmentRepository shipmentRepository, VehicleRepository vehicleRepository, LocationRepository locationRepository) {
        this.shipmentRepository = shipmentRepository;
        this.vehicleRepository = vehicleRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public Shipment createShipment(ShipmentDto shipmentDto) {
        Vehicle vehicle = vehicleRepository.findById(shipmentDto.getVehicleId())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id: " + shipmentDto.getVehicleId()));

        Location pickupLocation = locationRepository.findById(shipmentDto.getPickupLocationId())
                .orElseThrow(() -> new ResourceNotFoundException("Pickup location not found with id: " + shipmentDto.getPickupLocationId()));

        Location dropLocation = locationRepository.findById(shipmentDto.getDropLocationId())
                .orElseThrow(() -> new ResourceNotFoundException("Drop location not found with id: " + shipmentDto.getDropLocationId()));

        if (shipmentDto.getWeightKg() > vehicle.getCapacityKg()) {
            throw new IllegalArgumentException("Shipment weight exceeds vehicle capacity.");
        }

        if (shipmentDto.getScheduledDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Scheduled date cannot be in the past.");
        }

        Shipment shipment = new Shipment();
        shipment.setVehicle(vehicle);
        shipment.setPickupLocation(pickupLocation);
        shipment.setDropLocation(dropLocation);
        shipment.setWeightKg(shipmentDto.getWeightKg());
        shipment.setScheduledDate(shipmentDto.getScheduledDate());

        return shipmentRepository.save(shipment);
    }

    @Override
    public Optional<Shipment> getShipment(Long shipmentId) {
        return shipmentRepository.findById(shipmentId);
    }
}
