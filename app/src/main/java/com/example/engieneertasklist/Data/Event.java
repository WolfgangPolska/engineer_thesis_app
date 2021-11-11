package com.example.engieneertasklist.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Event {
    public static ArrayList<Event> eventsList = new ArrayList<>();

    public static ArrayList<Event> eventsForDate(LocalDate date)
    {
        ArrayList<Event> events = new ArrayList<>();

        for(Event event: eventsList)
        {
            if(event.getDate().equals(date))
            {
                events.add(event);
            }
        }

        return events;
    }

    private String name;
    private LocalDate date;
    private LocalTime Time;

    public Event(String name, LocalDate date, LocalTime time) {
        this.name = name;
        this.date = date;
        Time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return Time;
    }

    public void setTime(LocalTime time) {
        Time = time;
    }
}
