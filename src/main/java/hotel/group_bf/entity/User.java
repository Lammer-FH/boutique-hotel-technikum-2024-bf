package hotel.group_bf.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    // To Avoid Cascade Delete: This will disable Automatically delete all bookings associated with the user when the user is deleted.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)

    private List<Booking> bookings = new ArrayList<>(); // Initializing collection for Update User
}