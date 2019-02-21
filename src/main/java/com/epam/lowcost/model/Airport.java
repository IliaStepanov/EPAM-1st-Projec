package com.epam.lowcost.model;


import lombok.*;

@AllArgsConstructor
@Setter
@Builder
@Getter
public class Airport {
    private String code;
    private String cityEng;
    private String cityRus;
    private String nameEng;
    private String nameRus;
    private String countryEng;
    private String countryRus;
}
