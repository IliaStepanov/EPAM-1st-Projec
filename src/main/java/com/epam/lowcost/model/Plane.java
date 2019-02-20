package com.epam.lowcost.model;

import lombok.*;

@Setter
@Getter
public class Plane extends AbstractJdbcModel {
    private long id;
    private String model;
    private int businessPlacesNumber;
    private int economPlacesNumber;
    private boolean isDeleted;

    @Builder
    public Plane(Long id,
                 String model,
                 int businessPlacesNumber,
                 int economPlacesNumber,
                 boolean isDeleted) {
        super(id);
        this.model = model;
        this.businessPlacesNumber = businessPlacesNumber;
        this.economPlacesNumber = economPlacesNumber;
        this.isDeleted = isDeleted;
    }
}
