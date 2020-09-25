package com.manning.gia.todo.repository.mapdb;

import com.manning.gia.todo.DatabaseProfiles;
import com.manning.gia.todo.model.ToDoItem;
import com.manning.gia.todo.repository.ToDoRepository;
import org.mapdb.DB;
import org.mapdb.Serializer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@Profile(DatabaseProfiles.MAPDB)
class ToDoMapdbRepository implements ToDoRepository, InitializingBean {
    private static final long DEFAULT_START_ID = 1L;
    private static final String COLLECTION_NAME = "todo";

    private final AtomicLong ids = new AtomicLong(DEFAULT_START_ID);
    private final ConcurrentMap<Long, ToDoItem> todos;

    @SuppressWarnings("unchecked")
    ToDoMapdbRepository(DB mapdbDatabase) {
        this.todos = mapdbDatabase.hashMap(COLLECTION_NAME, Serializer.LONG, Serializer.JAVA)
                .createOrOpen();
    }

    @Override
    public ToDoItem findById(Long id) {
        return todos.get(id);
    }

    @Override
    public void save(ToDoItem toDoItem) {
        if (toDoItem.getId() == 0) {
            toDoItem.setId(ids.getAndIncrement());
        }
        todos.put(toDoItem.getId(), toDoItem);
    }

    @Override
    public List<ToDoItem> findAll() {
        return new ArrayList<>(todos.values());
    }

    @Override
    public void delete(ToDoItem toDoItem) {
        todos.remove(toDoItem.getId());
    }

    @Override
    public List<ToDoItem> findCompleted() {
        return findAll().stream().filter(ToDoItem::isCompleted).collect(Collectors.toList());
    }

    @Override
    public void afterPropertiesSet() {
        long maxExistingId = todos.keySet().stream()
                .max(Long::compareTo)
                .orElse(DEFAULT_START_ID);
        ids.set(maxExistingId);
    }
}
