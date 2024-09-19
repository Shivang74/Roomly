package com.shivag.roomly.service.interfac;

import com.shivag.roomly.dto.Response;
import com.shivag.roomly.entity.Booking;

public interface IBookingService {

    Response saveBooking(Long roomId, Long userId, Booking bookingRequest);

    Response findBookingByConfirmationCode(String confirmationCode);

    Response getAllBookings();

    Response cancelBooking(Long bookingId);

}
