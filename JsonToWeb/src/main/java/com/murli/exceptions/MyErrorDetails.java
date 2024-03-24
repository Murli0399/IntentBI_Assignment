package com.murli.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MyErrorDetails {

    private LocalDateTime timestamp;
    private String message;
    private String details;

}