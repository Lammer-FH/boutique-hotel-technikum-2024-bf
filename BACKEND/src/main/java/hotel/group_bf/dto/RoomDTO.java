package hotel.group_bf.dto;

import lombok.Data;

@Data
public class RoomDTO {
    private Long id;
    private String title;
    private String description;
    private String extra;
    private boolean available;
    private Double price;
}




