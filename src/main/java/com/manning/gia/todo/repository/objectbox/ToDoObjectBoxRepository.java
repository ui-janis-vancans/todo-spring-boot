package com.manning.gia.todo.repository.objectbox;

import com.manning.gia.todo.DatabaseProfiles;
import com.manning.gia.todo.model.ToDoItem;
import com.manning.gia.todo.repository.ToDoRepository;
import io.objectbox.Box;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile(DatabaseProfiles.OBJECTBOX)
public class ToDoObjectBoxRepository implements ToDoRepository {

    private final Box<ToDoItem> todoItemBox;

    public ToDoObjectBoxRepository(Box<ToDoItem> todoItemBox) {
        this.todoItemBox = todoItemBox;
    }

    @Override
    public ToDoItem findById(Long id) {
        return todoItemBox.get(id);
    }

    @Override
    public void save(ToDoItem toDoItem) {
        todoItemBox.put(toDoItem);
    }

    @Override
    public List<ToDoItem> findAll() {
        return todoItemBox.getAll();
    }

    @Override
    public void delete(ToDoItem toDoItem) {
        todoItemBox.remove(toDoItem);
    }
}
