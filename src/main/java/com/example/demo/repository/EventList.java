package com.example.demo.repository;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Event;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventList {

    private final List<Event> eventList;

    public EventList() {
        eventList = new ArrayList<>();
        eventList.add(new Event(1L, "Default Name", "Default Type", "Default Details", new Date()));
        eventList.add(new Event(2L, "Default Name2", "Default Type2", "Default Details2", new Date()));
        eventList.add(new Event(3L, "Default Name3", "Default Type3", "Default Details3", new Date()));
    }

    public Integer getDay(Event event) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(event.getEventDate());

        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    public Integer getMonth(Event event) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(event.getEventDate());

        return calendar.get(Calendar.MONTH);
    }

    public Integer getYear(Event event) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(event.getEventDate());

        return calendar.get(Calendar.YEAR);
    }
    public Integer getWeekNumber(Event event) {
        Calendar calendar = Calendar.getInstance();
        calendar.setMinimalDaysInFirstWeek(1);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(event.getEventDate());

        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    public List<Event> getEventList() {
        return eventList;
    }

//    public void setEventList(List<Event> eventList) {
//        this.eventList = eventList;
//    }

    public Event addToEventList(Event event){
        System.out.println(event);
        if(event.getId() == null) {
            event.setId(0L);
        }

        eventList.add(event);
        return event;
    }

    public Event modifyEvent(Long id, Event event) {
        Event event1 = eventList.stream().filter(el -> el.getId().equals(id)).findFirst().orElseThrow(() -> {
            throw new NotFoundException("Event nie został znaleziony");
        });

        event1.setName(event.getName());
        event1.setDetails(event.getDetails());
        event1.setType(event.getType());
        event1.setEventDate(event.getEventDate());

        return event1;
    }

    public void deleteEvent(Long id)  {
        Event event1 = eventList.stream().filter(el -> el.getId().equals(id)).findFirst().orElseThrow(() -> {
            throw new NotFoundException("Event nie został znaleziony");
        });

        eventList.remove(event1);
    }

    public Event getEventById(Long id) {
        return eventList.stream().filter(el -> el.getId().equals(id)).findFirst().orElseThrow(() -> {throw new NotFoundException("Event nie został znaleziony");});
    }

    public List<Event> getEventsByDay(int day) {
        return eventList.stream().filter(el -> getDay(el).equals(day)).collect(Collectors.toList());
    }


    public List<Event> getEventsByWeek(Integer weekNumber) {
        return eventList.stream().filter(el -> getWeekNumber(el).equals(weekNumber)).collect(Collectors.toList());
    }

    public List<Event> getEventsByMonth(Integer monthNumber) {
        return eventList.stream().filter(el -> getMonth(el).equals(monthNumber)).collect(Collectors.toList());
    }

    public String getDetailsByEvent(Event event) throws Throwable{
        Event event1 = eventList.stream().filter(el -> el == event).findFirst().orElseThrow(() -> {
            throw new NotFoundException("Event nie został znaleziony");
        });

        return event1.getDetails();
    }
    

}
