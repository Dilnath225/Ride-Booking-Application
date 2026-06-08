package com.NextGen.Ride_Booking_Application.dto;

import lombok.Data;

@Data
public class BookingRequest {
    private String passengerName;
    private String pickupLocation;
    private String dropoffLocation;
}
