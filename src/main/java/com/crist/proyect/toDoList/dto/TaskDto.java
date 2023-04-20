package com.crist.proyect.toDoList.dto;

import com.crist.proyect.toDoList.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskDto extends JpaRepository<Task,Integer> {
}
