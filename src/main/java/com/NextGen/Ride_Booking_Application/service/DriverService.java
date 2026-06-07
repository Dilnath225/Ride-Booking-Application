package com.NextGen.Ride_Booking_Application.service;
import com.NextGen.Ride_Booking_Application.entity.Driver;
import com.NextGen.Ride_Booking_Application.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    //add a new driver to the system
    public Driver addDriver(Driver driver){

        return driverRepository.save(driver);
    }

    //to  show the available drivers in the system
    public List<Driver> getAvailableDrivers(){
        return driverRepository.findByAvailable(true);
    }

    //to update the driver status
    public Driver updateDriverStatus(Long id, boolean isAvailable){
        Optional<Driver> optionalDriver = driverRepository.findById(id);
        if (optionalDriver.isPresent()){
            Driver driver = optionalDriver.get();
            driver.setAvailable(isAvailable);
            return  driverRepository.save(driver);
        }throw new RuntimeException("Driver is not found with relevant id" + id);
    }


}
