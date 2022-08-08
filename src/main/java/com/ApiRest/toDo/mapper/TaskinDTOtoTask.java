package com.ApiRest.toDo.mapper;

import com.ApiRest.toDo.persistence.entity.Task;
import com.ApiRest.toDo.persistence.entity.TaskStatus;
import com.ApiRest.toDo.service.dto.TaskDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskinDTOtoTask implements IMapper<TaskDTO, Task> {

    @Override
    public Task map(TaskDTO in) {
        Task task = new Task();
        task.setTitle(in.getTitle());
        task.setDescription(in.getDescription());
        task.setEta(in.getEta());
        task.setCreatedDate(LocalDateTime.now());
        task.setFinished(false);
        task.setTaskStatus(TaskStatus.ON_TIME);
        return task;
    }
}
