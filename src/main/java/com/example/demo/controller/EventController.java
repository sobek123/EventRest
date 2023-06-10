/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

import com.example.demo.model.Event;
import com.example.demo.service.EventService;
import com.itextpdf.text.DocumentException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RequestMapping("/events")
@RestController
@AllArgsConstructor
public class EventController {
    
    private final EventService eventService;
    
    @GetMapping
    public List<Event> getTextJSON(){
        List<Event> events = eventService.getEvents();

        for(Event event: events){
            if(!event.getLinks().hasSize(2)) {
                event.add(linkTo(EventController.class).slash(event.getId()).withSelfRel());
                event.add(linkTo(EventController.class).withRel("allEvents"));
            }
        }
        return events;
    }
    
    @GetMapping("/{eventId}")
    public Event getEvent(@PathVariable("eventId") Long id) throws Exception {
        Event event = eventService.getEventById(id);
        if(!event.getLinks().hasSize(2)) {
            event.add(linkTo(EventController.class).slash(event.getId()).withSelfRel());
            event.add(linkTo(EventController.class).withRel("allEvents"));
        }
        return event;
    }
    
    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventService.addEvent(event);
    }
    
    @PutMapping("/{eventId}")
    public Event updateEvent(@PathVariable("eventId") Long id,@RequestBody Event event) throws Throwable {
        return eventService.modifyEvent(id,event);
    }
    
    @DeleteMapping("/{eventId}")
    public void deleteEvent(@PathVariable("eventId") Long id) throws Throwable {
        eventService.deleteEvent(id);
    }


    @GetMapping("/day/{eventId}")
    public List<Event> getEventsByDay(@PathVariable("eventId")Integer day) {
        return eventService.getEventsByDay(day);
    }

    @GetMapping("/week/{eventId}")
    public List<Event> getEventsByWeek(@PathVariable("eventId")Integer weekNumber) {
        return eventService.getEventsByWeek(weekNumber);
    }

    @GetMapping("/month/{eventId}")
    public List<Event> getEventsByMonth(@PathVariable("eventId")Integer monthNumber) {
        return eventService.getEventsByMonth(monthNumber);
    }

    @PostMapping("/event/{eventId}")
    public String getDetailsByEvent(@RequestBody Event event) throws  Throwable{
        return eventService.getDetailsByEvent(event);

    }

    @GetMapping("/file")
    public byte[] downloadFile() throws DocumentException {
        return eventService.downloadFile();
    }

}
