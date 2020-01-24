package com.example.cinexfirebase;

public class Seats {
    private String ID;
    private String seatValue;
    private Boolean isTaken;

    public Seats(){}

    public Seats(String ID, String seatValue, Boolean isTaken){
        this.ID=ID;
        this.seatValue=seatValue;
        this.isTaken=isTaken;
    }

    public String getID() {
        return ID;
    }

    public Boolean getIsTaken() {
        return isTaken;
    }

    public String getSeatValue() {
        return seatValue;
    }
}
