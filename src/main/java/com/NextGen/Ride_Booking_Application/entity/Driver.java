package com.NextGen.Ride_Booking_Application.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name= "drivers")

public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String vehicaleModel;
    private String vehicleNumber;
    private boolean available = true;


    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
