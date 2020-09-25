package com.manning.gia.todo.repository.objectbox;

import com.manning.gia.todo.DatabaseProfiles;
import com.manning.gia.todo.model.ToDoItem;
import com.manning.gia.todo.model.ToDoItem_;
import com.manning.gia.todo.repository.ToDoRepository;
import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.query.QueryBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.List;

@Service
@Profile(DatabaseProfiles.OBJECTBOX)
public class ToDoObjectBoxRepository implements ToDoRepository {

    private final BoxStore objectStore;
    private final Box<ToDoItem> todoItemBox;

    public ToDoObjectBoxRepository(BoxStore objectStore, Box<ToDoItem> todoItemBox) {
        this.objectStore = objectStore;
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

    @Override
    public List<ToDoItem> findCompleted() {
        QueryBuilder<ToDoItem> completedQuery = todoItemBox.query();
        completedQuery.equal(ToDoItem_.completed, true);
        return completedQuery.build().find();
    }

    @PreDestroy
    public void preDestroy() {
        objectStore.close();
        objectStore.closeThreadResources();
    }
}
