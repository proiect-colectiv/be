package com.proiect_colectiv.model.DTO;

import java.time.LocalDate;

public class ReservationDTO {
    private Integer maxPlayersNumber;
    private Integer startHour;
    //reservation duration in hours, since startHour
    private Integer duration;
    private LocalDate date;
    private Long locationId;

}
