package com.example.demo.service;

import com.example.demo.dto.VehicleDto;
import com.example.demo.entity.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleService {
    Vehicle addVehicle(VehicleDto vehicleDto);
    List<Vehicle> getVehiclesByUser(Long userId);
    Optional<Vehicle> findById(Long id);
}
