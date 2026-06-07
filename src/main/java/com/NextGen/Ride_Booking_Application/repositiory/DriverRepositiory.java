package com.NextGen.Ride_Booking_Application.repositiory;
import com.NextGen.Ride_Booking_Application.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public class DriverRepositiory {

    @Repository
    public interface DriverRepository extends JpaRepository <Driver, Long>{

        List<Driver> findByAvailable(boolean available);


    }


}
