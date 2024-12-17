package com.maybank.assignment.controller;

import com.maybank.assignment.dto.GenericResponse;
import com.maybank.assignment.dto.TaskSearchHistory;
import com.maybank.assignment.entity.Task;
import com.maybank.assignment.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/V1/maybank/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping
    public ResponseEntity<GenericResponse<TaskSearchHistory>> taskList(@RequestParam(name = "search",required = false) String search,
                                                                       @RequestParam(defaultValue = "0") int pageNo,
                                                                       @RequestParam(defaultValue = "10") int pageSize,
                                                                       @RequestParam(name = "sortBy",required = false) String sortBy,
                                                                       @RequestParam(defaultValue = "DESC",required = false) String sortOrder) {
        try {
            TaskSearchHistory taskSearchHistory = taskService.getAllTaskList(search, pageNo, pageSize, sortBy, sortOrder);
            log.info("task Search History{}", taskSearchHistory);
            if (!taskSearchHistory.getData().isEmpty()) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), true, "Task  Records found", taskSearchHistory), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), true, "Task Records not found", taskSearchHistory), HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            TaskSearchHistory taskSearchHistory = new TaskSearchHistory();
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something went wrong", taskSearchHistory), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/allTasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> allTasks = taskService.getAllTasks();
        log.info("All Tasks:{}", allTasks);
        return ResponseEntity.ok(allTasks);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {
        log.info("Task By Id Request{}", taskId);
        Task taskById = taskService.getTaskById(taskId);
        log.info("Task By Id Response{}", taskById);
        return ResponseEntity.ok(taskById);
    }

    @PostMapping("/save")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        log.info("Task Save Request{}", task);
        Task taskResponse = taskService.createTask(task);
        log.info("Task Save Response{}", taskResponse);
        return ResponseEntity.ok(taskResponse);

    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task task) {
        log.info("Update Request Task:{}:{}", taskId, task);
        Task updateTaskResponse = taskService.updateTask(taskId, task);
        log.info("Update Response Task:{}", updateTaskResponse);
        return ResponseEntity.ok(updateTaskResponse);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        log.info("Delete Request Task:{}", taskId);
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }


}
