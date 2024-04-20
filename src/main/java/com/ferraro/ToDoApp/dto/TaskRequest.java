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

    @Size(min = 1, max = 100)
    @NotBlank
    private String title;

    @Size(min = 1, max = 500)
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @NotNull
    @FutureOrPresent
    @Column(columnDefinition = "TIMESTAP")
    private LocalDateTime date;


    @Enumerated(EnumType.STRING)
    private Category category;
}
