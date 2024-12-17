package com.maybank.assignment.dto;

import lombok.*;

@Data
public class MetaData {
    private Long totalRecords;
    private Long totalPages;
    private int currentPage;
    private int recordsPerPage;
}
