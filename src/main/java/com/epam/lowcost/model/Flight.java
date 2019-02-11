package com.epam.lowcost.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
    private long id;
    private long initiatedPrice;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
}
