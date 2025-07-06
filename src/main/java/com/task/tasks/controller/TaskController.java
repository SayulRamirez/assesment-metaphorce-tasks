package com.task.tasks.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.task.tasks.service.TaskService;
import com.task.tasks.dto.Task;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService service) {
        this.taskService = service;
    }

    @PostMapping("/")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Task> updateTask(@RequestParam Long id, @RequestBody Task task) {
        return ResponseEntity.ok(taskService.updateTask(id, task));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@RequestParam Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<Task>> getAllTask() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }
}
