package com.NextGen.Ride_Booking_Application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passengerName;
    private String pickupLocation;
    private String dropoffLocation;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    private String status; // CONFIRMED, COMPLETED, CANCELLED

    private LocalDateTime bookingTime;
}
