package com.ferraro.ToDoApp.entities;

import com.ferraro.ToDoApp.enums.Category;
import com.ferraro.ToDoApp.enums.Priority;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 100)
    @NotBlank
    private String title;

    @Size(min = 1, max = 500)
    private String description;

    @NotNull
    @Column(columnDefinition = "boolean default false")
    private Boolean isChecked;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Column(columnDefinition = "TIMESTAP")
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private Category category;


}
