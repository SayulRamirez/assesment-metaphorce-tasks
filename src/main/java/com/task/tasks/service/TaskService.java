package com.task.tasks.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.tasks.dto.Task;
import com.task.tasks.repository.TaskRepository;

@Service
public class TaskService {
    @Autowired
    private TaskRepository repository;

    Logger logger = LogManager.getLogger(TaskService.class);

    public Task createTask(Task task) {
        Task newTask = null;

        newTask = this.repository.save(task);

        return newTask;
    }

    public List<Task> getAllTasks() {
        List<Task> list = null;

        list = this.repository.findAll();

        return list;
    }

    public Task getTaskById(Long taskId) {
        Task data = null;

        data = this.repository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found " + taskId));

        return data;
    }

    public Task updateTask(Long taskId, Task taskToUpdate) {
        Task update = null;

        update = getTaskById(taskId);

        update.setTitle(taskToUpdate.getTitle());
        update.setCompleted(taskToUpdate.isCompleted());

        update = this.repository.saveAndFlush(update);

        return update;
    }

    public boolean deleteTask(Long id) {
        boolean flag = false;
        Task taskDelete = null;

        try {
            taskDelete = getTaskById(id);
            
            if(taskDelete != null) {
                this.repository.delete(taskDelete);
                flag = true;
            }
        } catch(Exception e) {
            logger.warn("Error to delete Task[" + id + "]");
        } 
        
        return flag;
    }
}