package com.crist.proyect.toDoList.services.impl;

import com.crist.proyect.toDoList.dto.TaskDto;
import com.crist.proyect.toDoList.dto.UsersDto;
import com.crist.proyect.toDoList.models.Role;
import com.crist.proyect.toDoList.models.Task;
import com.crist.proyect.toDoList.models.User;
import com.crist.proyect.toDoList.services.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TaskServiceImpl implements ITaskService {

    @Autowired
    private TaskDto repositoryTask;

    @Autowired
    private UsersDto repositortyUsers;

    @Override
    public List<Task> listTask() {
        if (getUserContext().getRol() == Role.USER ) {
            return repositoryTask.findByOwner(getUserContext().getId());
        }
        return repositoryTask.findAll();
    }

    @Override
    public Task getInfoTask(Integer id) {
        Task task = repositoryTask.findById(id).orElse(null);
        if (task != null && Objects.equals(task.getOwner(), getUserContext().getId())){
            return task;
        }
        return null;
    }

    @Override
    public Task createTask(Task task) {
        var taskCreated = Task.builder()
                .nameTask(task.getNameTask())
                .descriptionTask(task.getDescriptionTask())
                .owner(getUserContext())
                .startDate(task.getStartDate())
                .limitDate(task.getLimitDate())
                .isComplet(task.isComplet())
                .build();
        return repositoryTask.save(taskCreated);
    }

    @Override
    public Task updateTask(Integer id,Task task) {
        Task findTask = repositoryTask.findById(id).orElse(null);
        if (findTask != null && Objects.equals(findTask.getOwner(), getUserContext().getId())){
           if (task.getNameTask() != null){
               findTask.setNameTask(task.getNameTask());
           }
           if (task.getDescriptionTask() != null){
               findTask.setDescriptionTask(task.getDescriptionTask());
           }
            if (task.getLimitDate() != null){

                    findTask.setLimitDate(task.getLimitDate());

            }
            if (task.getStartDate() != null){
                findTask.setStartDate(task.getStartDate());
            }
            if (task.isComplet() != findTask.isComplet()){
                findTask.setComplet(task.isComplet());
            }
           return repositoryTask.save(findTask);
        }
        return null;
    }

    @Override
    public void deleteTask(Integer id) throws Exception {
        Task task = repositoryTask.findById(id).orElse(null);
        if (task != null && Objects.equals(task.getOwner(), getUserContext().getId())){
             repositoryTask.delete(task);

        }else{
            throw new ChangeSetPersister.NotFoundException();
        }

    }

    private User getUserContext(){
        UserDetails userLogin = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return repositortyUsers.findByEmail(userLogin.getUsername()).get();
    }


}
