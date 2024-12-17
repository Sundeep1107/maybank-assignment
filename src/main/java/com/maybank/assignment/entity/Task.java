package com.maybank.assignment.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "task_id")
    private Long taskId;

    @Column(name="title",unique = true)
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="status")
    private String status;//0 Indicates Not Completed; 1 Indicates Completed

    @Column(name="completed")
    private boolean completed;

}
