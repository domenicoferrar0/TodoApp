package com.ferraro.ToDoApp.service;

import com.ferraro.ToDoApp.dto.TaskDTO;
import com.ferraro.ToDoApp.dto.TaskRequest;
import com.ferraro.ToDoApp.entities.Task;
import com.ferraro.ToDoApp.enums.Category;
import com.ferraro.ToDoApp.enums.Priority;
import com.ferraro.ToDoApp.exceptions.TaskNotFoundException;
import com.ferraro.ToDoApp.mapper.TaskMapper;
import com.ferraro.ToDoApp.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    @Autowired
    private TaskMapper mapper;

    public TaskDTO createTask(TaskRequest request) {
        Task task = mapper.requestToTask(request);
        return mapper.taskToDto(repository.save(task));
    }

    public Task findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    public boolean deleteTask(Long id){
        Task task = findById(id);
        repository.delete(task);
        return true;
    }

    public TaskDTO checkTask(Long id){
        Task task = findById(id);
        task.setIsChecked(true);
        return mapper.taskToDto(repository.save(task));
    }

    public TaskDTO editTask(Long id, TaskRequest request){
        if(!repository.existsById(id)){
            throw new TaskNotFoundException(id);
        }
        Task task = mapper.requestToTask(request);
        task.setId(id);
        return mapper.taskToDto(repository.save(task));
    }

    public Page<TaskDTO> allTasks(int page, int pageSize){
        Pageable pageable = PageRequest.of(page-1, pageSize, Sort.by("date").descending());
        return repository.findAll(pageable)
                .map(mapper::taskToDto);
    }

    public Page<TaskDTO> tasksFiltered(int page, int pageSize, Category category, Boolean isChecked, Priority priority){
      if(category == null && isChecked == null && priority == null) {
          return allTasks(page, pageSize);
      }
        Pageable pageable = PageRequest.of(page-1, pageSize, Sort.by("date").descending());
        return repository.findAllBy(priority, category, isChecked, pageable)
                .map(mapper::taskToDto);
    }

    public Page<TaskDTO> taskBySearchterm(int page, int pageSize, String search){
        Pageable pageable = PageRequest.of(page-1, pageSize, Sort.by("date").descending());
        return repository.findBySearch(search, pageable)
                .map(mapper::taskToDto);
    }

    public TaskDTO findDtoById(Long id){
        return repository.findById(id)
                .map(mapper::taskToDto)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }




}
