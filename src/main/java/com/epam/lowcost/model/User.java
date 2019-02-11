package com.epam.lowcost.model;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {

    private long id;
    private String email;
    private String password;
    private boolean isAdmin;
    private String firstName;
    private String lastName;
    private String documentInfo;
    private LocalDateTime birthday;

}