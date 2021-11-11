package com.example.engieneertasklist.Data;

import com.example.engieneertasklist.Data.Event;

import java.time.LocalTime;
import java.util.ArrayList;

public class HourEvent {
    LocalTime time;
    ArrayList<Event> events;

    public LocalTime getTime() {
        return time;
    }

    public HourEvent(LocalTime time, ArrayList<Event> events) {
        this.time = time;
        this.events = events;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }
}
