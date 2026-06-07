package com.NextGen.Ride_Booking_Application.controller;
import com.NextGen.Ride_Booking_Application.entity.Driver;
import com.NextGen.Ride_Booking_Application.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/drivers")
@CrossOrigin(origins = "*") // to get the access for the frontend

public class DriverController {
    @Autowired
    private DriverService driverService;

    //to add a new driver in the system(POST method)
    @PostMapping("/add")
    public Driver addDriver(@RequestBody Driver driver){
        return driverService.addDriver(driver);
    }

    //to check the available drivers(GET method)
    @GetMapping("/available")
    public List<Driver> getAvailableDrivers(){
        return driverService.getAvailableDrivers();

    }

    //to check the driver status
    @PutMapping("/{id}/status")
    public Driver updateDriverStatus(@PathVariable Long id, @RequestParam boolean isAvailable) {
        return driverService.updateDriverStatus(id, isAvailable);
    }


}
