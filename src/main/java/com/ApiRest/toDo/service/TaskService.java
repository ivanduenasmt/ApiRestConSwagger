package com.ApiRest.toDo.service;

import com.ApiRest.toDo.exception.ToDoExceptions;
import com.ApiRest.toDo.mapper.TaskinDTOtoTask;
import com.ApiRest.toDo.persistence.entity.Task;
import com.ApiRest.toDo.persistence.entity.TaskStatus;
import com.ApiRest.toDo.persistence.repository.TaskRepository;
import com.ApiRest.toDo.service.dto.TaskDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository repository;

    private TaskinDTOtoTask mapper;

    public TaskService(TaskRepository repository, TaskinDTOtoTask mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Task save(TaskDTO taskDTO){
        return this.repository.save(mapper.map(taskDTO));
    }

    public List<Task> findAll(){
        return this.repository.findAll();
    }

    public List<Task> findAllByTaskStatus(TaskStatus status){
        return this.repository.findAllByTaskStatus(status);
    }

    @Transactional
    public void updateTaskAsFinished(Long id){
        Optional<Task> optionalTask = this.repository.findById(id);
        if(optionalTask.isEmpty())
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        this.repository.markTaskAsFinished(id);
    }

    public boolean deleteById(Long id){
        Optional<Task> optionalTask = this.repository.findById(id);
        if(optionalTask.isEmpty())
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        this.repository.deleteById(id);
        return true;
    }
}
