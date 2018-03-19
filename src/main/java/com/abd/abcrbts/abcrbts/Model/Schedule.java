package com.abd.abcrbts.abcrbts.Model;

import javax.persistence.*;

@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="departure")
    private String departure;

    @Column(name="destination")
    private String destination;

    @Column(name="time")
    private String time;

    @OneToOne
    private Bus bus;

    @Column(name="monday",columnDefinition = "BOOLEAN DEFAULT 0")
    private Boolean monday;
    @Column(name="tuesday",columnDefinition = "BOOLEAN DEFAULT 0")
    private Boolean tuesday;
    @Column(name="wednesday",columnDefinition = "BOOLEAN DEFAULT 0")
    private Boolean wednesday;
    @Column(name="thursday",columnDefinition = "BOOLEAN DEFAULT 0")
    private Boolean thursday;
    @Column(name="friday",columnDefinition = "BOOLEAN DEFAULT 0")
    private Boolean friday;
    @Column(name="saturday",columnDefinition = "BOOLEAN DEFAULT 0")
    private Boolean saturday;
    @Column(name="sunday",columnDefinition = "BOOLEAN DEFAULT 0")
    private Boolean sunday;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Boolean getMonday() {
        return monday;
    }

    public void setMonday(Boolean monday) {
        this.monday = monday;
    }

    public Boolean getTuesday() {
        return tuesday;
    }

    public void setTuesday(Boolean tuesday) {
        this.tuesday = tuesday;
    }

    public Boolean getWednesday() {
        return wednesday;
    }

    public void setWednesday(Boolean wednesday) {
        this.wednesday = wednesday;
    }

    public Boolean getThursday() {
        return thursday;
    }

    public void setThursday(Boolean thursday) {
        this.thursday = thursday;
    }

    public Boolean getFriday() {
        return friday;
    }

    public void setFriday(Boolean friday) {
        this.friday = friday;
    }

    public Boolean getSaturday() {
        return saturday;
    }

    public void setSaturday(Boolean saturday) {
        this.saturday = saturday;
    }

    public Boolean getSunday() {
        return sunday;
    }

    public void setSunday(Boolean sunday) {
        this.sunday = sunday;
    }
}
