package com.example.todoapp.repository;

import com.example.todoapp.entity.ToDo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TodoRepositoryImpl implements TodoRepository{
    private final List<ToDo> toDoList = new ArrayList<>();
    private long nextId = 1;

    @Override
    public ToDo save(ToDo todo) {
        if (todo.getId() == null) {
            todo.setId(nextId++);
        } else {
            deleteById(todo.getId());
        }
        toDoList.add(todo);
        return todo;
    }

    @Override
    public List<ToDo> findAll() {
        return new ArrayList<>(toDoList);
    }

    @Override
    public Optional<ToDo> findById(Long id) {
        return toDoList.stream()
                .filter(todo -> todo.getId().equals(id))
                .findFirst();
    }

    @Override
    public void deleteById(Long id) {
        toDoList.removeIf(todo -> todo.getId().equals(id));
    }
}
