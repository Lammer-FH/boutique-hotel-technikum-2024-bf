package hotel.group_bf.controller;

import hotel.group_bf.dto.UserDTO;
import hotel.group_bf.entity.User;
import hotel.group_bf.mapping.Mapper;
import hotel.group_bf.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final Mapper mapper;

    public UserController(UserService userService, Mapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
        User user = mapper.mapToUser(userDTO);
        userService.saveUser(user);
        return ResponseEntity.ok("User registered successfully");
    }
}
