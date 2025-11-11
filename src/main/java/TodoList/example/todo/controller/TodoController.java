package TodoList.example.todo.controller;


import TodoList.example.todo.model.Todo;
import TodoList.example.todo.service.TodoService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Todo> getAllTodos() {
        return service.getAllTodos();
    }

    @GetMapping("/{todoid}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long todoid) {
        Optional<Todo> todo=service.getTodoById(todoid);
        if(todo.isPresent()){
            Todo res=todo.get();

            HttpHeaders header=new HttpHeaders();
            header.add("todo is",todo.get().getId()+"");
            return new ResponseEntity<>(res, header,HttpStatus.OK);

        }
        else{
            return ResponseEntity.notFound().build();
        }



    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        return service.createTodo(todo);
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
        return service.updateTodo(id, todo);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
        service.deleteTodo(id);
    }
}
