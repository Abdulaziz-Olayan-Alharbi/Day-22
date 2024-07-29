package com.example.lab_5_event.Controller;

import com.example.lab_5_event.Api.ApiResponse;
import com.example.lab_5_event.Model.Event;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/event")
public class EventController {

    ArrayList<Event> events = new ArrayList<>();

    @PostMapping("/add")
    public ResponseEntity addEvent(@Valid @RequestBody Event event, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        events.add(event);
        return ResponseEntity.status(HttpStatus.OK).body(event);
    }

    @GetMapping("/get/{index}")
    public ResponseEntity getEvent(@Valid @PathVariable int index,Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        return ResponseEntity.status(HttpStatus.OK).body(events.get(index));
    }

    @PutMapping("/update/{index}")
    public ResponseEntity updateEvent(@Valid@PathVariable int index ,@Valid@RequestBody Event event ,Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        events.set(index, event);
        return ResponseEntity.status(HttpStatus.OK).body(events.get(index));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteEvent(@PathVariable int index) {
        events.remove(index);
        return ResponseEntity.status(HttpStatus.OK).body("event deleted");
    }

    @GetMapping("/events")
    public ResponseEntity getEvents() {
        return ResponseEntity.status(HttpStatus.OK).body(events);
    }

    @PutMapping("/capacity/{index}")
    public ResponseEntity changeCapacity (@Valid@PathVariable int index , @Valid@RequestBody int capacity,Errors errors){
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        events.get(index).setCapacity(capacity);
        return ResponseEntity.status(HttpStatus.OK).body(events.get(index));
    }

    @GetMapping("search/{id}")
    public ResponseEntity getById(@Valid@PathVariable String id,Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        for (Event event : events) {
            if (event.getId().equals(id)){
                return ResponseEntity.status(HttpStatus.OK).body(event);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body("not found");
    }
}
