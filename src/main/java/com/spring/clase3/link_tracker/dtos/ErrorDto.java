package com.spring.clase3.link_tracker.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorDto {

    private String description;
    private HttpStatus status;

}
