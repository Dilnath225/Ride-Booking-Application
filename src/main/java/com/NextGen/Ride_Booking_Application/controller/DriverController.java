package com.NextGen.Ride_Booking_Application.controller;

import com.NextGen.Ride_Booking_Application.entity.Driver;
import com.NextGen.Ride_Booking_Application.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
@CrossOrigin(origins = "*") // to get the access for the frontend
public class DriverController {

    @Autowired
    private DriverService driverService;

    // to add a new driver in the system (POST method)
    @PostMapping("/add")
    public ResponseEntity<Driver> addDriver(@RequestBody Driver driver) {
        Driver savedDriver = driverService.addDriver(driver);
        return new ResponseEntity<>(savedDriver, HttpStatus.CREATED);
    }

    // to get all drivers
    @GetMapping
    public ResponseEntity<List<Driver>> getAllDrivers() {
        return ResponseEntity.ok(driverService.getAllDrivers());
    }

    // to check the available drivers (GET method)
    @GetMapping("/available")
    public ResponseEntity<List<Driver>> getAvailableDrivers() {
        return ResponseEntity.ok(driverService.getAvailableDrivers());
    }

    // to update the driver status
    @PutMapping("/{id}/status")
    public ResponseEntity<Driver> updateDriverStatus(@PathVariable Long id, @RequestParam boolean isAvailable) {
        return ResponseEntity.ok(driverService.updateDriverStatus(id, isAvailable));
    }
}

