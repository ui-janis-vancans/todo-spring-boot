package com.manning.gia.todo.web;

import com.manning.gia.todo.model.ToDoItem;
import com.manning.gia.todo.repository.ToDoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/todo-items")
public class ToDoRestController {

    private final ToDoRepository toDoRepository;

    public ToDoRestController(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @PostMapping
    public void insertItem(@RequestParam String name) {
        ToDoItem toDoItem = new ToDoItem();
        toDoItem.setName(name);
        toDoRepository.save(toDoItem);
    }

    @GetMapping
    public List<ToDoItem> getAll() {
        return toDoRepository.findAll();
    }

    @GetMapping("/completed")
    public List<ToDoItem> getCompleted() {
        return toDoRepository.findCompleted();
    }
}
