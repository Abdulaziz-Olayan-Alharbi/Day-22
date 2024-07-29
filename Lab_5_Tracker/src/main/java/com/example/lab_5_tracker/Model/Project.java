package com.example.lab_5_tracker.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {
    @NotEmpty
    @Size(min = 2, message = "id must be more than 2 digits")
    private String id;
    @NotEmpty
    @Size(min = 8, message = "title must be more than 8 digits")
    private String title;
    @NotEmpty
    @Size(min = 15, message = "description must be more than 15 digits")
    private String description;
    @NotEmpty
    @Pattern(regexp = "^(Not Started|In Progress|completed)$")
    private String status;
    @NotEmpty
    @Size(min = 6, message = "company name must be more than 6 digits")
    private String companyName;

}
