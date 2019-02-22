package com.epam.lowcost.model;


import lombok.*;

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
