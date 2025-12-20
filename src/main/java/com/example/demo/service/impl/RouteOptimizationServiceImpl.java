package com.example.demo.service.impl;

import com.example.demo.entity.RouteOptimizationResult;
import com.example.demo.entity.Shipment;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RouteOptimizationResultRepository;
import com.example.demo.repository.ShipmentRepository;
import com.example.demo.service.RouteOptimizationService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RouteOptimizationServiceImpl implements RouteOptimizationService {

    private final RouteOptimizationResultRepository resultRepository;
    private final ShipmentRepository shipmentRepository;

    public RouteOptimizationServiceImpl(RouteOptimizationResultRepository resultRepository, ShipmentRepository shipmentRepository) {
        this.resultRepository = resultRepository;
        this.shipmentRepository = shipmentRepository;
    }

    @Override
    public RouteOptimizationResult optimizeRoute(Long shipmentId) {
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment not found with id: " + shipmentId));

        // Dummy distance and fuel calculation
        double distance = calculateDummyDistance(shipment);
        double fuel = calculateDummyFuelUsage(shipment, distance);

        RouteOptimizationResult result = new RouteOptimizationResult();
        result.setShipment(shipment);
        result.setOptimizedDistanceKm(distance);
        result.setEstimatedFuelUsageL(fuel);

        return resultRepository.save(result);
    }

    @Override
    public Optional<RouteOptimizationResult> getResult(Long resultId) {
        return resultRepository.findById(resultId);
    }

    private double calculateDummyDistance(Shipment shipment) {
        // This is a dummy calculation. A real implementation would use a mapping service.
        double lat1 = shipment.getPickupLocation().getLatitude();
        double lon1 = shipment.getPickupLocation().getLongitude();
        double lat2 = shipment.getDropLocation().getLatitude();
        double lon2 = shipment.getDropLocation().getLongitude();
        
        // Haversine formula
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = 6371 * c; // Distance in km

        return distance > 0 ? distance : 100.0; // Ensure positive distance
    }

    private double calculateDummyFuelUsage(Shipment shipment, double distance) {
        double fuelEfficiency = shipment.getVehicle().getFuelEfficiency();
        if (fuelEfficiency <= 0) {
            // Avoid division by zero and return a sensible default
            return distance > 0 ? 10.0 : 0;
        }
        double fuelUsage = distance / fuelEfficiency;
        return fuelUsage > 0 ? fuelUsage : 10.0; // Ensure positive fuel usage
    }
}
