package com.simpalm.meetingtracker;

/**
 * Created by Simpalm on 10/19/16.
 */

public class eventDetail {

    String eventTitle, eventDesc, eventDateTime, eventLocation;

    public eventDetail(String eventTitle, String eventLocation, String eventDateTime, String eventDesc) {
        this.eventTitle = eventTitle;
        this.eventLocation = eventLocation;
        this.eventDateTime = eventDateTime;
        this.eventDesc = eventDesc;
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
