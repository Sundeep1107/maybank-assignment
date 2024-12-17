package com.maybank.assignment.service;

//import com.maybank.assignment.dto.TaskSearchHistory;
import com.maybank.assignment.dto.TaskSearchHistory;
import com.maybank.assignment.entity.Task;

import java.util.List;

public interface TaskService {

    TaskSearchHistory getAllTaskList(String search, int pageNo, int pageSize, String sortBy, String sortOrder);

    List<Task> getAllTasks();

    Task getTaskById(Long taskId);

    Task createTask(Task task);

    Task updateTask(Long taskId,Task task);

    void deleteTask(Long taskId);

}
