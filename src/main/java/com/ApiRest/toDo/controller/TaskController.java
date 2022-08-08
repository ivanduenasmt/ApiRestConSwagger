package com.ApiRest.toDo.controller;

import com.ApiRest.toDo.persistence.entity.Task;
import com.ApiRest.toDo.persistence.entity.TaskStatus;
import com.ApiRest.toDo.service.TaskService;
import com.ApiRest.toDo.service.dto.TaskDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Task> save(@RequestBody TaskDTO dto){
        return new ResponseEntity<>(this.service.save(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Task>> findAll(){
        List<Task> taskList = this.service.findAll();
        return new ResponseEntity<>(taskList, HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Task>> findAllByTaskStatus(@PathVariable("status") TaskStatus status){
        return new ResponseEntity<>(this.service.findAllByTaskStatus(status),HttpStatus.OK);
    }

    @PatchMapping("/markAsFinished/{id}")
    public ResponseEntity<Void> markAsFinished(@PathVariable("id") Long id){
        this.service.updateTaskAsFinished(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        this.service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
