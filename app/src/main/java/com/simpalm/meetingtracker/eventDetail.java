package com.simpalm.meetingtracker;

/**
 * Created by Simpalm on 10/19/16.
 */

public class EventDetail {

    String eventTitle, eventDesc, eventDateTime, eventLocation;
    double eventLat, eventLong;

    public double getEventLat() {
        return eventLat;
    }

    public void setEventLat(double eventLat) {
        this.eventLat = eventLat;
    }

    public double getEventLong() {
        return eventLong;
    }

    public void setEventLong(double eventLong) {
        this.eventLong = eventLong;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public String getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(String eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }
}
