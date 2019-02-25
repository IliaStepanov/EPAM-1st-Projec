package com.epam.lowcost.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Flight extends AbstractJdbcModel {
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

    @Builder
    public Flight(Long id,
                  long initialPrice,
                  Plane plane,
                  LocalDateTime departureDate,
                  LocalDateTime arrivalDate,
                  Airport departureAirport,
                  Airport arrivalAirport,
                  long placePriorityPrice,
                  long businessPrice,
                  long luggagePrice,
                  boolean isDeleted) {
        super(id);
        this.initialPrice = initialPrice;
        this.plane = plane;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.placePriorityPrice = placePriorityPrice;
        this.businessPrice = businessPrice;
        this.luggagePrice = luggagePrice;
        this.isDeleted = isDeleted;
    }


}
