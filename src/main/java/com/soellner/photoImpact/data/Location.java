package com.soellner.photoImpact.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Alex on 11.06.2016.
 */
@Entity
public class Location {
    private int _id;
    private int _userID;
    private double _latitude;
    private double _longitude;
    private String _dateTime;



    public Location() {
    }

    @Id
    @GeneratedValue
    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
    }

    public int getUserID() {
        return _userID;
    }

    public void setUserID(int userID) {
        _userID = userID;
    }

    public double getLatitude() {
        return _latitude;
    }

    public void setLatitude(double latitude) {
        _latitude = latitude;
    }

    public double getLongitude() {
        return _longitude;
    }

    public void setLongitude(double longitude) {
        _longitude = longitude;
    }

    public String getDateTime() {
        return _dateTime;
    }

    public void setDateTime(String dateTime) {
        _dateTime = dateTime;
    }


    @Override
    public String toString() {
        return "Location{" +
                "_id='" + _id + '\'' +
                ", _userID=" + _userID +
                ", _latitude=" + _latitude +
                ", _longitude=" + _longitude +
                ", _dateTime='" + _dateTime + '\'' +
                '}';
    }
}
