package com.crist.proyect.toDoList.dto;

import com.crist.proyect.toDoList.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersDto extends JpaRepository<User,Integer> {
}
