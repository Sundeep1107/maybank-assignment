package com.maybank.assignment.service.impl;

import com.maybank.assignment.dto.TaskSearchHistory;
import com.maybank.assignment.entity.Task;
import com.maybank.assignment.repository.TaskRepository;
import com.maybank.assignment.service.TaskService;
import com.maybank.assignment.util.CommonUtil;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;


    @Override
    public TaskSearchHistory getAllTaskList(String search, int pageNo, int pageSize, String sortBy, String sortOrder) {
        int actualPage = pageNo > 0 ? pageNo - 1 : 0;

        sortBy = (sortBy == null || sortBy.isEmpty()) ? "title" : sortBy;

        sortOrder = (sortOrder == null || sortOrder.isEmpty()) ? "description" : sortOrder;

        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
        Pageable pageable = PageRequest.of(actualPage, pageSize, sort);

        Page<Task> pages = taskRepository.findAllTask(search, pageable);
        return mapToTaskSearchResult(pageNo, pageSize, pages);

    }

    private TaskSearchHistory mapToTaskSearchResult(int pageNo, int pageSize, Page<Task> tasks) {
        TaskSearchHistory taskSearchHistory = new TaskSearchHistory();
        taskSearchHistory.setMetaData(CommonUtil.getMetaData(tasks.getTotalElements(), (long) tasks.getTotalPages(), pageNo, pageSize));
        taskSearchHistory.setData(tasks.getContent());
        return taskSearchHistory;
    }


    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @Override
    @Transactional
    public Task createTask(Task task) {
        try {
            return taskRepository.save(task);
        }
        catch (DataIntegrityViolationException ex) {
            // Handle the exception and return a custom error message
            throw new IllegalArgumentException("Title already in taken.");
        }
    }

    @Override
    @Transactional
    public Task updateTask(Long taskId, Task taskDetails) {
        Task task = getTaskById(taskId);
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setCompleted(taskDetails.isCompleted());
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }


}
