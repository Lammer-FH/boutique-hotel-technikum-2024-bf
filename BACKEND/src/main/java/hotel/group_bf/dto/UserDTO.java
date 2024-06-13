package hotel.group_bf.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String password;
    private String firstName;
    private String lastName;
    private String email;
}
