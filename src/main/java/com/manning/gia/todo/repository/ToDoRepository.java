package com.manning.gia.todo.repository;

import com.manning.gia.todo.model.ToDoItem;

import java.util.List;

public interface ToDoRepository {

    ToDoItem findById(Long id);

    void save(ToDoItem toDoItem);

    List<ToDoItem> findAll();

    void delete(ToDoItem toDoItem);

    List<ToDoItem> findCompleted();
}
