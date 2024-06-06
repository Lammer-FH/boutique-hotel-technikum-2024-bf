package hotel.group_bf.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingDTO {
    private Long id;
    private boolean breakfast;
    private LocalDate startDate;
    private LocalDate endDate;
    private String firstName;
    private String lastName;
    private String email;
    private Long roomId;
    private Long userId;
}


