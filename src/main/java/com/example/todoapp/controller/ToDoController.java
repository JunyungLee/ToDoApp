package com.example.todoapp.controller;

import com.example.todoapp.entity.ToDo;
import com.example.todoapp.service.ToDoServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todo")
public class ToDoController {
    private final ToDoServiceImpl toDoService;

    public ToDoController(ToDoServiceImpl toDoService) {
        this.toDoService = toDoService;
    }

    //TODO 항목 작성 및 등록 -> TODO 항목은 제목과 상세 내용으로 구성
    @ApiOperation(value = "Todo 항목 작성 API", notes = "Todo 항목 작성 후 저장한다.")
    @PostMapping("/create")
    public ResponseEntity<ToDo> createToDo(@RequestBody ToDo toDo) {
        return ResponseEntity.ok(toDoService.createTodo(toDo));
    }
    //TODO 항목(제목) 리스트 불러오기
    @ApiOperation(value = "ToDo 항목 리스트 불러오는 API", notes = "각 항목의 리스트를 모두 불러온다.")
    @GetMapping("/load/list")
    public ResponseEntity<Map<Long,String>> getAllTodoList() {
        Map<Long, String> todos = toDoService.getAllTodos().stream().collect(Collectors.toMap(ToDo::getId, ToDo::getTitle));
        return ResponseEntity.ok(todos);
    }
    //TODO 항목 제목 및 상세 내용 조회
    @ApiOperation(value = "상세 내용 조회 API",notes = "각 항목의 상세 내용을 조회한다.")
    @GetMapping("/load/{id}")
    public ResponseEntity<ToDo> getTodoById(@PathVariable Long id) {
        return toDoService.getTodoById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    //TODO 항목 업데이트
    @ApiOperation(value = "항목 업데이트 API", notes = "각 항목의 내용을 수정한다")
    @PutMapping("/update/{id}")
    public ResponseEntity<ToDo> updateTodo(@PathVariable Long id, @RequestBody ToDo updateTodo) {
        return ResponseEntity.ok(toDoService.updateTodo(id, updateTodo));
    }
    //TODO 항목 삭제
    @ApiOperation(value = "ToDo 항목 삭제", notes = "각 항목의 내용을 삭제한다.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ToDo> deleteTodo(@PathVariable Long id) {
        toDoService.deleteTodo(id);
        return (ResponseEntity<ToDo>) ResponseEntity.status(HttpStatus.OK);
    }

}
