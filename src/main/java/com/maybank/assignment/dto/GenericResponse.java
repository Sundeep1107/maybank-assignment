package com.maybank.assignment.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse<T> {
    private int httpStatus;
    private boolean success;
    private String message;
    private T data;

}
