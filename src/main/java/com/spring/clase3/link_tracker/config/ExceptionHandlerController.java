package com.spring.clase3.link_tracker.config;

import com.spring.clase3.link_tracker.dtos.ErrorDto;
import com.spring.clase3.link_tracker.exceptions.InvalidIdException;
import com.spring.clase3.link_tracker.exceptions.InvalidUrlException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler({InvalidUrlException.class, InvalidIdException.class})
    public ResponseEntity<ErrorDto> handleInvalidUrl(Exception e) {
        ErrorDto errorDto = new ErrorDto(e.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorDto, errorDto.getStatus());
    }

}
