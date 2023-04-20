package com.crist.proyect.toDoList.controllers;

import com.crist.proyect.toDoList.models.Task;
import com.crist.proyect.toDoList.services.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/task/")
public class taskController {

    @Autowired
    private ITaskService taskService;

    @GetMapping()
    public ResponseEntity<?> listTask(){
        return ResponseEntity.ok(taskService.listTask());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getTask(@PathVariable Integer id) {
        return ResponseEntity.ok(taskService.getInfoTask(id));
    }

    @PostMapping("create")
    public ResponseEntity<?> createTask(@RequestBody Task payload){
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(payload));
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<?> updateTask(@RequestBody Task payload , @PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.updateTask(id,payload));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Integer id) throws Exception {
      try {
          taskService.deleteTask(id);
          return  ResponseEntity.status(HttpStatus.OK).body("Task Deleted");
      }catch (Exception e
      ){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The Task cant be deleted");
      }

    }

}
