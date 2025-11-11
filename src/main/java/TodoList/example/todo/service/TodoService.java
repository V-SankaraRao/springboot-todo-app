package TodoList.example.todo.service;


import TodoList.example.todo.model.Todo;
import TodoList.example.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public List<Todo> getAllTodos() {
        return repository.findAll();
    }

    public Optional<Todo> getTodoById(Long id) {
        return repository.findById(id);
    }

    public Todo createTodo(Todo todo) {
        return repository.save(todo);
    }

    public Todo updateTodo(Long id, Todo todo) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setTitle(todo.getTitle());
                    existing.setCompleted(todo.isCompleted());
                    return repository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Todo not found"));
    }

    public void deleteTodo(Long id) {
        repository.deleteById(id);
    }
}
