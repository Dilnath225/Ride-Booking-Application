package com.NextGen.Ride_Booking_Application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponse {
    private Long bookingId;
    private String passengerName;
    private String pickupLocation;
    private String dropoffLocation;
    private String status;
    private String driverName;
    private String vehicleModel;
    private String vehicleNumber;
    private LocalDateTime bookingTime;
}
