package com.example.lab_5_tracker.Controller;

import com.example.lab_5_tracker.Api.ApiResponse;
import com.example.lab_5_tracker.Model.Project;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/project")
public class projectController {

    ArrayList<Project> projects = new ArrayList<Project>();

    @PostMapping("/add")
    public ResponseEntity addProject(@Valid @RequestBody Project project, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        projects.add(project);
        return ResponseEntity.status(HttpStatus.OK).body(project);
    }

    @GetMapping("/get/{index}")
    public ResponseEntity getProject(@Valid @PathVariable int index,Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        return ResponseEntity.status(HttpStatus.OK).body(projects.get(index));
    }

    @PutMapping("/update/{index}")
    public ResponseEntity updateProject(@Valid@PathVariable int index ,@Valid@RequestBody Project project ,Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        projects.set(index, project);
        return ResponseEntity.status(HttpStatus.OK).body(projects.get(index));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteProject(@PathVariable int index) {
        projects.remove(index);
        return ResponseEntity.status(HttpStatus.OK).body("Project deleted");
    }

    @PutMapping("/status/update/{index}")
    public ResponseEntity updateStatus(@Valid@PathVariable int index,Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        if(projects.get(index).getStatus().equals("Not Started")){
            projects.get(index).setStatus("In Progress");
        }else {
            projects.get(index).setStatus("completed");
        }
        return ResponseEntity.status(HttpStatus.OK).body(projects.get(index));
    }

    @GetMapping("/search/{title}")
    public ResponseEntity searchProjectByTitle(@Valid@PathVariable String title,Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        for (Project project : projects) {
            if (project.getTitle().equals(title)) {
                return ResponseEntity.status(HttpStatus.OK).body(project);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body("there is no project has this title");
    }

    @GetMapping("/projects")
    public ResponseEntity displayAllProjects() {
        return ResponseEntity.status(HttpStatus.OK).body(projects);
    }

    @GetMapping("/company/{company}")
    public ResponseEntity getProjectsByCompanyName(@Valid@PathVariable String company,Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        ArrayList<Project> sameCompanyName = new ArrayList<>();
        for (Project project : projects) {
            if (project.getCompanyName().equals(company)) {
                sameCompanyName.add(project);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(sameCompanyName);
    }


}
