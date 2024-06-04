package hotel.group_bf.controller;

import hotel.group_bf.dto.BookingDTO;
import hotel.group_bf.entity.Booking;
import hotel.group_bf.mapping.Mapper;
import hotel.group_bf.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/*@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private Mapper mapper;

    @PostMapping
    public Booking saveBooking(@RequestBody BookingDTO bookingDTO) {
        Booking booking = mapper.mapToBooking(bookingDTO);
        return bookingService.saveBooking(booking);
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/availability")
    public boolean checkAvailability(@RequestParam Long roomId, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return bookingService.isRoomAvailable(roomId, startDate, endDate);
    }

    @GetMapping("/{id}/confirmation")
    public BookingDTO getBookingConfirmation(@PathVariable Long id) {
        Booking booking = bookingService.getBookingById(id);
        return mapper.mapToBookingDTO(booking);
    }

    @PutMapping("/{id}")
    public BookingDTO updateBooking(@PathVariable Long id, @RequestBody BookingDTO bookingDTO) {
        Booking booking = mapper.mapToBooking(bookingDTO);
        Booking updatedBooking = bookingService.updateBooking(id, booking);
        return mapper.mapToBookingDTO(updatedBooking);
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        bookingService.deleteBookingById(id);
    }
}*/

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private Mapper mapper;

    @PostMapping
    public Booking saveBooking(@RequestBody BookingDTO bookingDTO) {
        Booking booking = mapper.mapToBooking(bookingDTO);
        return bookingService.saveBooking(booking);
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/availability")
    public boolean checkAvailability(@RequestParam Long roomId, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return bookingService.isRoomAvailable(roomId, startDate, endDate);
    }

    @GetMapping("/{id}/confirmation")
    public BookingDTO getBookingConfirmation(@PathVariable Long id) {
        Booking booking = bookingService.getBookingById(id);
        return mapper.mapToBookingDTO(booking);
    }

    @PutMapping("/{id}")
    public BookingDTO updateBooking(@PathVariable Long id, @RequestBody BookingDTO bookingDTO) {
        Booking booking = mapper.mapToBooking(bookingDTO);
        Booking updatedBooking = bookingService.updateBooking(id, booking);
        return mapper.mapToBookingDTO(updatedBooking);
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        bookingService.deleteBookingById(id);
    }
}
