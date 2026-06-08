package com.NextGen.Ride_Booking_Application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "drivers")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "vehicale_model")
    private String vehicleModel;

    private String vehicleNumber;

    private Boolean available;

    @PrePersist
    public void prePersist() {
        if (available == null) {
            available = true;
        }
    }
}
