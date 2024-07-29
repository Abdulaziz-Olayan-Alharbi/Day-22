package com.example.lab_5_event.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Event {

    @NotEmpty
    @Size(min = 2, message = "id must be more than 2 digits")
    private String id;
    @NotEmpty
    @Size(min = 15, message = "description must be more than 15 digits")
    private String description;
    @NotNull
    @Min(15)
    @Max(200)
    private int capacity;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate;

}
