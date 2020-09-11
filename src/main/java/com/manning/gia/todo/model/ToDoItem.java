package com.manning.gia.todo.model;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class ToDoItem {

    @Id
    private long id;
    private String name;
    private boolean completed;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return id + ": " + name + " [completed: " + completed + "]";
    }
}
