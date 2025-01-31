package com.example.todoapp.repository;

import com.example.todoapp.entity.ToDo;

import java.util.List;
import java.util.Optional;

public interface TodoRepository{
    ToDo save(ToDo todo);
    List<ToDo> findAll();

    Optional<ToDo> findById(Long id);

    void deleteById(Long id);
}
