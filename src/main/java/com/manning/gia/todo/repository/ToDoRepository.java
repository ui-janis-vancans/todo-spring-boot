package com.manning.gia.todo.repository;

import com.manning.gia.todo.model.MyObjectBox;
import com.manning.gia.todo.model.ToDoItem;
import io.objectbox.Box;
import io.objectbox.BoxStore;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.List;

@Service
public class ToDoRepository {

    private BoxStore store = MyObjectBox.builder().name("demo-db").build();
    private Box<ToDoItem> box = store.boxFor(ToDoItem.class);

    public ToDoRepository() {
    }

    public ToDoItem findOne(Long id) {
        return box.get(id);
    }

    public void save(ToDoItem toDoItem) {
        box.put(toDoItem);
    }

    public List<ToDoItem> findAll() {
        return box.getAll();
    }

    public void delete(ToDoItem toDoItem) {
        box.remove(toDoItem);
    }

    @PreDestroy
    public void clean() {
        store.close();
    }
}
