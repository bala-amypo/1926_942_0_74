package com.example.demo.service.impl;

import com.example.demo.dto.VehicleDto;
import com.example.demo.entity.User;
import com.example.demo.entity.Vehicle;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.service.VehicleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, UserRepository userRepository) {
        this.vehicleRepository = vehicleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Vehicle addVehicle(VehicleDto vehicleDto) {
        if (vehicleDto.getCapacityKg() <= 0) {
            throw new IllegalArgumentException("Capacity must be positive.");
        }
        User user = userRepository.findById(vehicleDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + vehicleDto.getUserId()));

        Vehicle vehicle = new Vehicle();
        vehicle.setUser(user);
        vehicle.setVehicleNumber(vehicleDto.getVehicleNumber());
        vehicle.setCapacityKg(vehicleDto.getCapacityKg());
        vehicle.setFuelEfficiency(vehicleDto.getFuelEfficiency());

        return vehicleRepository.save(vehicle);
    }

    @Override
    public List<Vehicle> getVehiclesByUser(Long userId) {
        return vehicleRepository.findByUserId(userId);
    }

    @Override
    public Optional<Vehicle> findById(Long id) {
        return vehicleRepository.findById(id);
    }
}
