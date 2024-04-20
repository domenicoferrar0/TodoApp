package com.ferraro.ToDoApp.controller;

import com.ferraro.ToDoApp.dto.TaskDTO;
import com.ferraro.ToDoApp.dto.TaskRequest;
import com.ferraro.ToDoApp.enums.Category;
import com.ferraro.ToDoApp.enums.Priority;
import com.ferraro.ToDoApp.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/api/v0")
public class TaskController {

    @Autowired
    private TaskService service;

    private final int pageSize = 10;

    @PostMapping("/task")
    public ResponseEntity<TaskDTO> newTask(@Valid @NonNull @RequestBody TaskRequest request){
        return ResponseEntity
                .ok(service.createTask(request));
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<TaskDTO> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.findDtoById(id));
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id){
        if(!service.deleteTask(id)){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Sorry, couldn't delete your task");
        }
        return ResponseEntity.ok("Task deleted successfully!");
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<TaskDTO> updateById(@PathVariable("id") Long id,
                                              @Valid @NonNull @RequestBody TaskRequest request){
        return ResponseEntity.ok(service.editTask(id, request));
    }

    @GetMapping("/tasks/{page}")
    public ResponseEntity<Page<TaskDTO>> findAllTasks(@PathVariable("page") int page){
        return ResponseEntity.ok(service.allTasks(page, pageSize));
    }

    @GetMapping("/tasks/{page}")
    public ResponseEntity<Page<TaskDTO>> findAllTasksByParams(@PathVariable("page") int page,
                                                              @RequestParam("category") Category category,
                                                              @RequestParam("priority")Priority priority,
                                                              @RequestParam("isChecked") boolean isChecked){
        return ResponseEntity.ok(service.tasksFiltered(page, pageSize, category, isChecked, priority));
    }

    @GetMapping("/tasks/{page}")
    public ResponseEntity<Page<TaskDTO>> findAllTasksBySearchterm(@PathVariable("page") int page,
                                                                  @RequestParam("searchterm")String searchterm){
        return ResponseEntity.ok(service.taskBySearchterm(page, pageSize, searchterm));
    }


}
