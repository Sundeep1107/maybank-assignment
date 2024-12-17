package com.maybank.assignment.dto;

import com.maybank.assignment.entity.Task;
import lombok.*;

import java.util.List;

@Data
public class TaskSearchHistory {
    private MetaData metaData;
    private List<Task> data;
}