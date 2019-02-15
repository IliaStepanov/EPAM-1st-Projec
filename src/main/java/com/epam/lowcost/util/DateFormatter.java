package com.epam.lowcost.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateFormatter {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00");


    public String format(LocalDateTime dateTime) {

        return formatter.format(dateTime);
    }

    public String customFormat ( LocalDateTime dateTime) {
        return  customFormatter.format(dateTime);
    }


}
