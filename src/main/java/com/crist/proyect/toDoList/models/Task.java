package com.crist.proyect.toDoList.models;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.Date;

@Entity
@Builder
public class Task {

    @Id
    @GeneratedValue
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

    public Task(Integer id, String nameTask, String descriptionTask, User owner, Date startDate, Date limitDate,boolean isComplet) {
        Id = id;
        this.nameTask = nameTask;
        this.descriptionTask = descriptionTask;
        this.owner = owner;
        this.startDate = startDate;
        this.limitDate = limitDate;
        this.isComplet = isComplet;
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


    public Integer getOwner() {
        return owner.getId();
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
