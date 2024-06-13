package hotel.group_bf.mapping;

import hotel.group_bf.dto.BookingDTO;
import hotel.group_bf.dto.RoomDTO;
import hotel.group_bf.dto.UserDTO;
import hotel.group_bf.entity.Booking;
import hotel.group_bf.entity.Room;
import hotel.group_bf.entity.User;
import hotel.group_bf.service.RoomService;
import hotel.group_bf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {

    @Autowired
    private RoomService roomService;

    @Autowired
    private UserService userService;

    public Booking mapToBooking(BookingDTO bookingDTO) {
        Booking booking = new Booking();
        booking.setId(bookingDTO.getId());
        booking.setBreakfast(bookingDTO.isBreakfast());
        booking.setStartDate(bookingDTO.getStartDate());
        booking.setEndDate(bookingDTO.getEndDate());
        booking.setFirstName(bookingDTO.getFirstName());
        booking.setLastName(bookingDTO.getLastName());
        booking.setEmail(bookingDTO.getEmail());

        Room room = roomService.getRoomById(bookingDTO.getRoomId());
        booking.setRoom(room);

        // If userId is not null, we assume the user is already registered
        if (bookingDTO.getUserId() != null) {
            User user = userService.findById(bookingDTO.getUserId());
            booking.setUser(user);
        }

        return booking;
    }

    public BookingDTO mapToBookingDTO(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setId(booking.getId());
        dto.setBreakfast(booking.isBreakfast());
        dto.setStartDate(booking.getStartDate());
        dto.setEndDate(booking.getEndDate());
        dto.setFirstName(booking.getFirstName());
        dto.setLastName(booking.getLastName());
        dto.setEmail(booking.getEmail());
        dto.setRoomId(booking.getRoom().getId());
        if (booking.getUser() != null) {
            dto.setUserId(booking.getUser().getId());
        }
        return dto;
    }

    public RoomDTO mapToRoomDTO(Room room) {
        RoomDTO dto = new RoomDTO();
        dto.setId(room.getId());
        dto.setTitle(room.getTitle());
        dto.setDescription(room.getDescription());
        dto.setExtra(room.getExtra());
        dto.setAvailable(room.isAvailable());
        dto.setPrice(room.getPrice());
        return dto;
    }

    public Room mapToRoom(RoomDTO roomDTO) {
        Room room = new Room();
        room.setId(roomDTO.getId());
        room.setTitle(roomDTO.getTitle());
        room.setDescription(roomDTO.getDescription());
        room.setExtra(roomDTO.getExtra());
        room.setAvailable(roomDTO.isAvailable());
        room.setPrice(roomDTO.getPrice());
        return room;
    }

    public List<RoomDTO> mapToRoomDTOList(List<Room> rooms) {
        return rooms.stream().map(this::mapToRoomDTO).collect(Collectors.toList());
    }

    public User mapToUser(UserDTO userDTO) {
        User user = new User();
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        return user;
    }

    public UserDTO mapToUserDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setPassword(user.getPassword());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        return dto;
    }

    public List<BookingDTO> mapToBookingDTOList(List<Booking> bookings) {
        return bookings.stream().map(this::mapToBookingDTO).collect(Collectors.toList());
    }
}
