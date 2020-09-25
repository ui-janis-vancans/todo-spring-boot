package com.manning.gia.todo.repository.mongo;

import com.manning.gia.todo.DatabaseProfiles;
import com.manning.gia.todo.model.ToDoItem;
import com.manning.gia.todo.repository.ToDoRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile(DatabaseProfiles.MONGO)
public class MongoRepository implements ToDoRepository {

    private final MongoTemplate template;
    private final CounterService counterService;

    public MongoRepository(MongoTemplate template, CounterService counterService) {
        this.template = template;
        this.counterService = counterService;
    }

    @Override
    public ToDoItem findById(Long id) {
        Query idQuery = new Query();
        idQuery.addCriteria(Criteria.where("id").is(id));
        return template.findOne(idQuery, ToDoItem.class);
    }

    @Override
    public void save(ToDoItem toDoItem) {
        if (toDoItem.getId() == 0) {
            toDoItem.setId(counterService.getNextTodoItemId());
        }
        template.save(toDoItem);
    }

    @Override
    public List<ToDoItem> findAll() {
        return template.findAll(ToDoItem.class);
    }

    @Override
    public void delete(ToDoItem toDoItem) {
        template.remove(toDoItem);
    }
}
