package com.NextGen.Ride_Booking_Application.service;

import com.NextGen.Ride_Booking_Application.dto.BookingRequest;
import com.NextGen.Ride_Booking_Application.dto.BookingResponse;
import com.NextGen.Ride_Booking_Application.entity.Booking;
import com.NextGen.Ride_Booking_Application.entity.Driver;
import com.NextGen.Ride_Booking_Application.exception.NoDriverAvailableException;
import com.NextGen.Ride_Booking_Application.exception.ResourceNotFoundException;
import com.NextGen.Ride_Booking_Application.repository.BookingRepository;
import com.NextGen.Ride_Booking_Application.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private DriverRepository driverRepository;

    // create a new booking and auto-assign an available driver
    public BookingResponse createBooking(BookingRequest request) {

        // find available drivers
        List<Driver> availableDrivers = driverRepository.findByAvailable(true);

        if (availableDrivers.isEmpty()) {
            throw new NoDriverAvailableException("No drivers are currently available. Please try again later.");
        }

        // assign the first available driver
        Driver assignedDriver = availableDrivers.get(0);

        // set driver as unavailable
        assignedDriver.setAvailable(false);
        driverRepository.save(assignedDriver);

        // create the booking
        Booking booking = new Booking();
        booking.setPassengerName(request.getPassengerName());
        booking.setPickupLocation(request.getPickupLocation());
        booking.setDropoffLocation(request.getDropoffLocation());
        booking.setDriver(assignedDriver);
        booking.setStatus("CONFIRMED");
        booking.setBookingTime(LocalDateTime.now());

        Booking savedBooking = bookingRepository.save(booking);

        return mapToResponse(savedBooking);
    }

    // get booking by id
    public BookingResponse getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));
        return mapToResponse(booking);
    }

    // get all bookings
    public List<BookingResponse> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // cancel a booking and free up the driver
    public BookingResponse cancelBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));

        // free up the driver
        Driver driver = booking.getDriver();
        if (driver != null) {
            driver.setAvailable(true);
            driverRepository.save(driver);
        }

        booking.setStatus("CANCELLED");
        Booking updatedBooking = bookingRepository.save(booking);

        return mapToResponse(updatedBooking);
    }

    // complete a ride
    public BookingResponse completeBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));

        // free up the driver
        Driver driver = booking.getDriver();
        if (driver != null) {
            driver.setAvailable(true);
            driverRepository.save(driver);
        }

        booking.setStatus("COMPLETED");
        Booking updatedBooking = bookingRepository.save(booking);

        return mapToResponse(updatedBooking);
    }

    // helper method to map Booking entity to BookingResponse DTO
    private BookingResponse mapToResponse(Booking booking) {
        BookingResponse response = new BookingResponse();
        response.setBookingId(booking.getId());
        response.setPassengerName(booking.getPassengerName());
        response.setPickupLocation(booking.getPickupLocation());
        response.setDropoffLocation(booking.getDropoffLocation());
        response.setStatus(booking.getStatus());
        response.setBookingTime(booking.getBookingTime());

        if (booking.getDriver() != null) {
            response.setDriverName(booking.getDriver().getName());
            response.setVehicleModel(booking.getDriver().getVehicleModel());
            response.setVehicleNumber(booking.getDriver().getVehicleNumber());
        }

        return response;
    }
}
