package hotel.group_bf.controller;

import hotel.group_bf.dto.BookingDTO;
import hotel.group_bf.entity.Booking;
import hotel.group_bf.entity.User;
import hotel.group_bf.mapping.Mapper;
import hotel.group_bf.service.BookingService;
import hotel.group_bf.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final UserService userService;
    private final Mapper mapper;

    @PostMapping
    public ResponseEntity<BookingDTO> saveBooking(@RequestBody BookingDTO bookingDTO) {
        Booking booking = mapper.mapToBooking(bookingDTO);

        // If userId is provided, find the user, otherwise, we create a new user
        if (bookingDTO.getUserId() != null) {
            User user = userService.findById(bookingDTO.getUserId());
            booking.setUser(user);
        } else {
            // Create a new user for this booking
            User user = new User();
            user.setFirstName(bookingDTO.getFirstName());
            user.setLastName(bookingDTO.getLastName());
            user.setEmail(bookingDTO.getEmail());
            user.setPassword("");
            userService.saveUser(user);
            booking.setUser(user);
        }

        Booking savedBooking = bookingService.saveBooking(booking);
        return ResponseEntity.ok(mapper.mapToBookingDTO(savedBooking));
    }

    @GetMapping
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        return ResponseEntity.ok(mapper.mapToBookingDTOList(bookingService.getAllBookings()));
    }

    @GetMapping("/availability")
    public ResponseEntity<Boolean> checkAvailability(@RequestParam Long roomId, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        boolean available = bookingService.isRoomAvailable(roomId, startDate, endDate);
        return ResponseEntity.ok(available);
    }

    @GetMapping("/{id}/confirmation")
    public ResponseEntity<BookingDTO> getBookingConfirmation(@PathVariable Long id) {
        Booking booking = bookingService.getBookingById(id);
        return ResponseEntity.ok(mapper.mapToBookingDTO(booking));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingDTO> updateBooking(@PathVariable Long id, @RequestBody BookingDTO bookingDTO) {
        Booking booking = mapper.mapToBooking(bookingDTO);

        if (bookingDTO.getUserId() != null) {
            User user = userService.findById(bookingDTO.getUserId());
            booking.setUser(user);
        } else {
            throw new IllegalArgumentException("User ID must be provided when updating a booking.");
        }

        Booking updatedBooking = bookingService.updateBooking(id, booking);
        return ResponseEntity.ok(mapper.mapToBookingDTO(updatedBooking));
    }
}
