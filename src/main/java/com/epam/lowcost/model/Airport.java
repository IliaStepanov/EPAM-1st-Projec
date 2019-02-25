package com.epam.lowcost.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@AllArgsConstructor
@Setter
@Builder
public class Airport {
    private String code;
    private String cityEng;
    private String cityRus;
    private String nameEng;
    private String nameRus;
    private String countryEng;
    private String countryRus;
}
