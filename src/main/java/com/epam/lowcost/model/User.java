package com.epam.lowcost.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class User extends AbstractJdbcModel {
    private String email;
    private String password;
    private boolean isAdmin;
    private String firstName;
    private String lastName;
    private String documentInfo;
    private LocalDateTime birthday;
    private boolean isDeleted;

    @Builder
    public User(Long id,
                String email,
                String password,
                boolean isAdmin,
                String firstName,
                String lastName,
                String documentInfo,
                LocalDateTime birthday,
                boolean isDeleted) {
        super(id);
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.documentInfo = documentInfo;
        this.birthday = birthday;
        this.isDeleted = isDeleted;
    }
}