package com.crist.proyect.toDoList.services;

import com.crist.proyect.toDoList.models.Task;

import java.util.List;

public interface ITaskService {

    List<Task> listTask();

    Task getInfoTask(Integer id);

    Task createTask(Task task);

    Task updateTask(Integer id ,Task task);

    void deleteTask(Integer id) throws Exception;

}
