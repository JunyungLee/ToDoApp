package com.example.todoapp.service;

import com.example.todoapp.entity.ToDo;

import java.util.List;
import java.util.Optional;

public interface ToDoService {
    ToDo createTodo(ToDo todo);
    List<ToDo> getAllTodos();
    Optional<ToDo> getTodoById(Long id);
    ToDo updateTodo(Long id, ToDo updatedTodo);
    void deleteTodo(Long id);

}
