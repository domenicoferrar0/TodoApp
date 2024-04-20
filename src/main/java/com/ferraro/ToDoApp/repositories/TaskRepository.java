package com.ferraro.ToDoApp.repositories;

import com.ferraro.ToDoApp.entities.Task;
import com.ferraro.ToDoApp.enums.Category;
import com.ferraro.ToDoApp.enums.Priority;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t from Task t WHERE (:priority IS NULL OR t.priority = :priority) "+
            "AND (:category IS NULL OR t.category = :category)"+
            "AND (:isChecked IS NULL OR t.isChecked = :isChecked)")
    Page<Task> findAllBy(@Param("priority") Priority priority, @Param("category") Category category,
                         @Param("isChecked") Boolean isChecked, Pageable pageable);
    @Query("SELECT t from Task t WHERE t.title LIKE %?1% OR t.description LIKE %?1%")
    Page<Task>findBySearch(String searchTerm, Pageable pageable);
}
