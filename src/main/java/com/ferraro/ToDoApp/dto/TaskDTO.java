package com.ferraro.ToDoApp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ferraro.ToDoApp.enums.Category;
import com.ferraro.ToDoApp.enums.Priority;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime date;

    private Category category;
}
