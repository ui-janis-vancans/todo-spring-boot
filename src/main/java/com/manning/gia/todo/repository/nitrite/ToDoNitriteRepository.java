package com.manning.gia.todo.repository.nitrite;

import java.util.List;

import com.manning.gia.todo.DatabaseProfiles;
import com.manning.gia.todo.model.ToDoItem;
import com.manning.gia.todo.repository.ToDoRepository;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.ObjectRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile(DatabaseProfiles.NITRITE)
public class ToDoNitriteRepository implements ToDoRepository {

    ObjectRepository<ToDoItem> repository;

    public ToDoNitriteRepository(Nitrite db) {
        this.repository = db.getRepository(ToDoItem.class);
    }

    @Override
    public ToDoItem findById(Long id) {
        return repository.getById(NitriteId.createId(id));
    }

    @Override
    public void save(ToDoItem toDoItem) {
        repository.update(toDoItem, true);
    }

    @Override
    public List<ToDoItem> findAll() {
        return repository.find().toList();
    }

    @Override
    public void delete(ToDoItem toDoItem) {
        repository.remove(toDoItem);
    }
}
