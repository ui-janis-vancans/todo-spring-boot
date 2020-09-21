package com.manning.gia.todo.config.objectbox;

import com.manning.gia.todo.DatabaseProfiles;
import com.manning.gia.todo.model.MyObjectBox;
import com.manning.gia.todo.model.ToDoItem;
import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.BoxStoreBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.File;

@Configuration
@Profile(DatabaseProfiles.OBJECTBOX)
class ObjectBoxConfig {

    @Bean
    BoxStore objectBoxStore() {
        BoxStoreBuilder storeBuilder = MyObjectBox.builder()
                .baseDirectory(new File("data"))
                .name("demo-db");
        return storeBuilder.build();
    }

    @Bean
    Box<ToDoItem> toDoBox(BoxStore objectBoxStore) {
        return objectBoxStore.boxFor(ToDoItem.class);
    }
}
