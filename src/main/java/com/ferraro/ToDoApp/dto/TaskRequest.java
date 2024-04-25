package com.ferraro.ToDoApp.dto;

import com.ferraro.ToDoApp.enums.Category;
import com.ferraro.ToDoApp.enums.Priority;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class TaskRequest {

    @Size(min = 1, max = 100, message = "Title surpassed the limit of 100 characters")
    @NotBlank(message = "Title cannot be empty")
    private String title;

    @Size(min = 1, max = 500, message = "Description surpassed the limit of 500 characters")
    private String description;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @NotNull(message = "Insert a proper date")
    @FutureOrPresent(message = "Date not valid, it cannot be in past")
    private LocalDateTime date;


    @Enumerated(EnumType.STRING)
    private Category category;
}
