package com.epam.lowcost.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class Ticket extends AbstractJdbcModel {
    private User user;
    private Flight flight;
    private boolean isBusiness;
    private boolean hasLuggage;
    private boolean placePriority;
    private long price;
    private LocalDateTime purchaseDate;
    private boolean isDeleted;

    @Builder
    public Ticket(Long id,
                  User user,
                  Flight flight,
                  boolean isBusiness,
                  boolean hasLuggage,
                  boolean placePriority,
                  long price,
                  LocalDateTime purchaseDate,
                  boolean isDeleted) {
        super(id);
        this.user = user;
        this.flight = flight;
        this.isBusiness = isBusiness;
        this.hasLuggage = hasLuggage;
        this.placePriority = placePriority;
        this.purchaseDate = purchaseDate;
        this.price = price;
        this.isDeleted = isDeleted;
    }
}
