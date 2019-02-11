package com.epam.lowcost.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plane {
    private long id;
    private String model;
    private int businessPlacesNumber;
    private int economPlacesNumber;
}
