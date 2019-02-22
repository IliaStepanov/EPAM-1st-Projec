package com.epam.lowcost.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Flight {
    private long id;
    private long initialPrice;
    private Plane plane;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private boolean isDeleted;
    private long placePriorityPrice;
    private long businessPrice;
    private long luggagePrice;


}
