package com.example.demo.service;

import com.example.demo.dto.LocationDto;
import com.example.demo.entity.Location;

import java.util.List;

public interface LocationService {
    Location createLocation(LocationDto locationDto);
    List<Location> getAllLocations();
}
