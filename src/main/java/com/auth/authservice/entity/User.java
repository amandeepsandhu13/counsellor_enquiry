package com.auth.authservice.entity;

import com.auth.authservice.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   // @Column(nullable = false)
    private String name;

   // @Column(nullable = false, unique = true)
    private String email;

    //@Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

}
