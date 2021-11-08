package com.proiect_colectiv.model;


import javax.persistence.ElementCollection;
import java.time.*;
import java.util.HashSet;
import java.util.Set;


public class SportiveLocation extends Entity{

    private String name;
    private String adress;
    private String description;
    private Double rentPrice;
    private LocalTime openTime;
    private LocalTime closeTime;
    private Set<Day> openDays;

    public SportiveLocation(Long ID,String name, String adress, String description, Double rentPrice, LocalTime openTime, LocalTime closeTime, Set<Day> openDays) {
        super(ID);
        this.name = name;
        this.adress = adress;
        this.description = description;
        this.rentPrice = rentPrice;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.openDays = openDays;
    }

    public SportiveLocation(String name, String adress, String description, Double rentPrice, LocalTime openTime, LocalTime closeTime, Set<Day> openDays) {
        this.name = name;
        this.adress = adress;
        this.description = description;
        this.rentPrice = rentPrice;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.openDays = openDays;
    }



    public SportiveLocation() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Double rentPrice) {
        this.rentPrice = rentPrice;
    }

    public LocalTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalTime openTime) {
        this.openTime = openTime;
    }

    public LocalTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalTime closeTime) {
        this.closeTime = closeTime;
    }

    public Set<Day> getOpenDays() {
        return openDays;
    }

    public void setOpenDays(Set<Day> openDays) {
        this.openDays = openDays;
    }

    @Override
    public String toString() {
        return "SportiveLocation{" +
                "name='" + name + '\'' +
                ", adress='" + adress + '\'' +
                ", description='" + description + '\'' +
                ", rentPrice=" + rentPrice +
                ", openTime=" + openTime +
                ", closeTime=" + closeTime +
                ", openDays=" + openDays +
                '}';
    }

    //TODO: image discussion
    //private String imagePath;

    //getAllSportiveLocations
    //getSportiveLocation/{id}


}
