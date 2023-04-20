package com.crist.proyect.toDoList.dto;

import com.crist.proyect.toDoList.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TaskDto extends JpaRepository<Task,Integer> {

    @Query(nativeQuery = true,value = "Select * From Task u Where u.owner_id = :user")
    List<Task> findByOwner(Integer user);

}
