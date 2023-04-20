package com.crist.proyect.toDoList.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.Date;

@Entity
public class Task {

    @Id
    private Integer Id;

    private String nameTask;

    private String descriptionTask;

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

    private Date startDate;

    private Date limitDate;

    private boolean isComplet;


    public Task() {
    }

    public Task(Integer id, String nameTask, String descriptionTask, User owner, Date startDate, Date limitDate) {
        Id = id;
        this.nameTask = nameTask;
        this.descriptionTask = descriptionTask;
        this.owner = owner;
        this.startDate = startDate;
        this.limitDate = limitDate;
        this.isComplet = false;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public String getDescriptionTask() {
        return descriptionTask;
    }

    public void setDescriptionTask(String descriptionTask) {
        this.descriptionTask = descriptionTask;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(Date limitDate) {
        this.limitDate = limitDate;
    }

    public boolean isComplet() {
        return isComplet;
    }

    public void setComplet(boolean complet) {
        isComplet = complet;
    }
}
