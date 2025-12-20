package com.example.demo.service.impl;

import com.example.demo.dto.LocationDto;
import com.example.demo.entity.Location;
import com.example.demo.repository.LocationRepository;
import com.example.demo.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public Location createLocation(LocationDto locationDto) {
        if (locationDto.getLatitude() < -90 || locationDto.getLatitude() > 90) {
            throw new IllegalArgumentException("Invalid latitude value.");
        }
        if (locationDto.getLongitude() < -180 || locationDto.getLongitude() > 180) {
            throw new IllegalArgumentException("Invalid longitude value.");
        }

        Location location = new Location();
        location.setName(locationDto.getName());
        location.setLatitude(locationDto.getLatitude());
        location.setLongitude(locationDto.getLongitude());

        return locationRepository.save(location);
    }

    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }
}
