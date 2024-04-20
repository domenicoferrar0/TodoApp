package com.ferraro.ToDoApp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ferraro.ToDoApp.enums.Category;
import com.ferraro.ToDoApp.enums.Priority;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskDTO {

    private Long id;

    private String title;

    private String description;

    private Boolean isChecked;

    private Priority priority;

    private LocalDateTime date;

    private Category category;
}
