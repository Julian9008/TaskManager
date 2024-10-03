package com.example.TaskManager.controller;

import com.example.TaskManager.model.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private List<Task> tasks = new ArrayList<>();

    // GET - Listar todas las tareas
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(tasks);
    }

    // POST - Crear una nueva tarea
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        tasks.add(task);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    // PUT - Marcar una tarea como completada
    @PutMapping("/{id}/complete")
    public ResponseEntity<Task> completeTask(@PathVariable int id) {  // Cambié Long a int
        for (Task task : tasks) {
            if (task.getId() == id) {  // Cambié equals a ==
                task.setCompleted(true);
                return ResponseEntity.ok(task);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // DELETE - Eliminar una tarea
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable int id) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == id) {
                tasks.remove(i);
                return ResponseEntity.noContent().build(); // Devuelve 204 No Content
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Devuelve 404 Not Found si no se encuentra
    }

}
