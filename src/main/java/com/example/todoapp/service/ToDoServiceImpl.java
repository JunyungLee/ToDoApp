package com.example.todoapp.service;

import com.example.todoapp.repository.TodoRepository;
import com.example.todoapp.entity.ToDo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoServiceImpl implements ToDoService {
    private final TodoRepository todoRepository;

    public ToDoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public ToDo createTodo(ToDo todo){
        return todoRepository.save(todo);
    }
    public List<ToDo> getAllTodos() {
        return todoRepository.findAll();
    }
    public Optional<ToDo> getTodoById(Long id) {
        return todoRepository.findById(id);
    }
    public ToDo updateTodo(Long id, ToDo updatedTodo) {
        return todoRepository.findById(id).map(todo -> {
            // 기존 객체의 필드 값만 변경
            todo.setTitle(updatedTodo.getTitle());
            todo.setDescription(updatedTodo.getDescription());

            return todoRepository.save(todo); // 기존 객체를 저장
        }).orElseThrow(() -> new RuntimeException("해당 할일을 찾을 수 없습니다."));
    }


    public void deleteTodo(Long id) {
        Optional<ToDo> existing = todoRepository.findById(id);
        if(existing.get() != null) {
            todoRepository.deleteById(id);
        } else {
            System.out.println("해당 할일이 없습니다!!!!!!!!!!");
        }
    }
}
