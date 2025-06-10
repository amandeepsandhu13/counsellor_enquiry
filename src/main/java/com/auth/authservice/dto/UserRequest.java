package com.auth.authservice.dto;

import com.auth.authservice.enums.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {

    private String name;
    private String email;
    private String password;
    private Role role;


}
