package com.manning.gia.todo.repository.mongo;

import com.manning.gia.todo.DatabaseProfiles;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
@Profile(DatabaseProfiles.MONGO)
public class CounterService {

    private final MongoTemplate mongoTemplate;

    public CounterService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
        Counter todoItemCounterSeq = new Counter();
        todoItemCounterSeq.setName(TODO_SEQ_NAME);
        todoItemCounterSeq.setSequence(0L);
        mongoTemplate.save(todoItemCounterSeq);
    }

    public static final String TODO_SEQ_NAME = "todo_id";

    public long getNextTodoItemId() {
        return increaseCounter(TODO_SEQ_NAME);
    }

    /**
     * @param counterName // to define counter name in database
     * @return
     * @author prabjot
     * this method will update the counter in database and return counter value
     */
    private long increaseCounter(String counterName) {
        Query query = new Query(Criteria.where("name").is(counterName));
        Update update = new Update().inc("sequence", 1);
        Counter counter = mongoTemplate.findAndModify(query, update, Counter.class); // return old Counter object
        return counter.getSequence();
    }
}
