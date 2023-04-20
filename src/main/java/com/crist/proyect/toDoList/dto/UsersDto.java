package com.crist.proyect.toDoList.dto;

import com.crist.proyect.toDoList.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UsersDto extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);

}
