package com.ferraro.ToDoApp.exceptions;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(Long id){
        super("Sorry couldn't find task with this id: "+id);
    }
}
