package com.NextGen.Ride_Booking_Application.service;

import com.NextGen.Ride_Booking_Application.entity.Driver;
import com.NextGen.Ride_Booking_Application.exception.ResourceNotFoundException;
import com.NextGen.Ride_Booking_Application.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    // add a new driver to the system
    public Driver addDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    // get all drivers in the system
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    // show the available drivers in the system
    public List<Driver> getAvailableDrivers() {
        return driverRepository.findByAvailable(true);
    }

    // update the driver status
    public Driver updateDriverStatus(Long id, boolean isAvailable) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found with id: " + id));
        driver.setAvailable(isAvailable);
        return driverRepository.save(driver);
    }
}

