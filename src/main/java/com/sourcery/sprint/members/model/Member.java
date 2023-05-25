package com.sourcery.sprint.members.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Member {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private Boolean softDelete;
    private String userType;
    private String token;

    public void clearPassword() {
        this.password = null;
    }
}
