package com.proiect_colectiv.model;

import java.time.*;

public class Reservation extends Entity{

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private User owner;
    //??
    //private List<User> invitedPlayers;
    private SportiveLocation reservedLocation;

    public Reservation(Long id, LocalDateTime startTime, LocalDateTime endTime, User owner, SportiveLocation reservedLocation) {
        super(id);
        this.startTime=startTime;
        this.endTime=endTime;
        this.owner=owner;
        this.reservedLocation=reservedLocation;
    }
}
