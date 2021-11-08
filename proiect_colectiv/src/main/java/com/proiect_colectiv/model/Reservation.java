package com.proiect_colectiv.model;

import java.time.*;

public class Reservation extends Entity{

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private User owner;
    private SportiveLocation reservedLocation;
    private Integer maxNumberOfPlayers;
    private Integer currentNumberOfPlayers;

    public Reservation(Long id, LocalDateTime startTime, LocalDateTime endTime, User owner, SportiveLocation reservedLocation) {
        super(id);
        this.startTime=startTime;
        this.endTime=endTime;
        this.owner=owner;
        this.reservedLocation=reservedLocation;
    }

    public Reservation(LocalDateTime startTime, LocalDateTime endTime, User owner, SportiveLocation reservedLocation, Integer maxNumberOfPlayers, Integer currentNumberOfPlayers) {
        this.startTime=startTime;
        this.endTime=endTime;
        this.owner=owner;
        this.reservedLocation=reservedLocation;
        this.maxNumberOfPlayers=maxNumberOfPlayers;
        this.currentNumberOfPlayers=currentNumberOfPlayers;
    }

    public Reservation() {

    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public SportiveLocation getReservedLocation() {
        return reservedLocation;
    }

    public void setReservedLocation(SportiveLocation reservedLocation) {
        this.reservedLocation = reservedLocation;
    }

    public Integer getMaxNumberOfPlayers() {
        return maxNumberOfPlayers;
    }

    public void setMaxNumberOfPlayers(Integer maxNumberOfPlayers) {
        this.maxNumberOfPlayers = maxNumberOfPlayers;
    }

    public Integer getCurrentNumberOfPlayers() {
        return currentNumberOfPlayers;
    }

    public void setCurrentNumberOfPlayers(Integer currentNumberOfPlayers) {
        this.currentNumberOfPlayers = currentNumberOfPlayers;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", maxNumberOfPlayers=" + maxNumberOfPlayers +
                ", currentNumberOfPlayers=" + currentNumberOfPlayers +
                '}';
    }
}
