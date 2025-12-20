package com.example.demo.service;

import com.example.demo.entity.RouteOptimizationResult;

import java.util.Optional;

public interface RouteOptimizationService {
    RouteOptimizationResult optimizeRoute(Long shipmentId);
    Optional<RouteOptimizationResult> getResult(Long resultId);
}
