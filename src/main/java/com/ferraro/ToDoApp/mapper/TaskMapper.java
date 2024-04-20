package com.ferraro.ToDoApp.mapper;

import com.ferraro.ToDoApp.dto.TaskDTO;
import com.ferraro.ToDoApp.dto.TaskRequest;
import com.ferraro.ToDoApp.entities.Task;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskMapper {

    public Task requestToTask(TaskRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());

        if (request.getDescription() != null) {
            task.setDescription(request.getDescription());
        }
        task.setPriority(request.getPriority());
        task.setDate(request.getDate());
        if (request.getDate() != null) {
            task.setCategory(request.getCategory());
        }
        return task;
    }

    public List<TaskDTO> taskDTOList(List<Task> tasks){
        return tasks.stream()
                .map(this::taskToDto)
                .collect(Collectors.toList());
    }

    public TaskDTO taskToDto(Task entity){
        TaskDTO task = new TaskDTO();
        task.setId(entity.getId());
        task.setIsChecked(entity.getIsChecked());
        task.setTitle(entity.getTitle());

        if (entity.getDescription() != null) {
            task.setDescription(entity.getDescription());
        }
        task.setPriority(entity.getPriority());
        task.setDate(entity.getDate());
        if (entity.getDate() != null) {
            task.setCategory(entity.getCategory());
        }
        return task;
    }
}
