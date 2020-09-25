package com.manning.gia.todo.model;

import io.objectbox.annotation.Entity;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Entity
@Document(collection = "toDoItems")
public class ToDoItem implements Serializable {

    @org.springframework.data.annotation.Id
    @io.objectbox.annotation.Id
    @org.dizitart.no2.objects.Id
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
