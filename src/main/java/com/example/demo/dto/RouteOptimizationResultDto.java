package com.example.demo.dto;

import java.time.LocalDateTime;

public class RouteOptimizationResultDto {
    private Long id;
    private Long shipmentId;
    private double optimizedDistanceKm;
    private double estimatedFuelUsageL;
    private LocalDateTime generatedAt;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public double getOptimizedDistanceKm() {
        return optimizedDistanceKm;
    }

    public void setOptimizedDistanceKm(double optimizedDistanceKm) {
        this.optimizedDistanceKm = optimizedDistanceKm;
    }

    public double getEstimatedFuelUsageL() {
        return estimatedFuelUsageL;
    }

    public void setEstimatedFuelUsageL(double estimatedFuelUsageL) {
        this.estimatedFuelUsageL = estimatedFuelUsageL;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }
}
