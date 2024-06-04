package hotel.group_bf.service;

import hotel.group_bf.entity.Booking;
import hotel.group_bf.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking updateBooking(Long id, Booking updatedBooking) {
        return bookingRepository.findById(id).map(booking -> {
            booking.setBreakfast(updatedBooking.isBreakfast());
            booking.setStartDate(updatedBooking.getStartDate());
            booking.setEndDate(updatedBooking.getEndDate());
            booking.setFirstName(updatedBooking.getFirstName());
            booking.setLastName(updatedBooking.getLastName());
            booking.setEmail(updatedBooking.getEmail());
            booking.setRoom(updatedBooking.getRoom());
            booking.setUser(updatedBooking.getUser());
            return bookingRepository.save(booking);
        }).orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public boolean isRoomAvailable(Long roomId, LocalDate startDate, LocalDate endDate) {
        List<Booking> bookings = bookingRepository.findBookingsByRoomIdAndDateRange(roomId, startDate, endDate);
        return bookings.isEmpty();
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    public void deleteBookingById(Long id) {
        bookingRepository.deleteById(id);
    }
}