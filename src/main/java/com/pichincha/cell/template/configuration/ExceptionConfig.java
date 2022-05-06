package com.pichincha.cell.template.configuration;

import com.pichincha.cell.template.exception.CustomizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;

@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class ExceptionConfig {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exampleException(Exception e) {
        log.error(e.getMessage());
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler({CustomizedException.class, DataIntegrityViolationException.class})
    public ResponseEntity<Object> customizedException(Exception e) {
        log.error(e.getMessage());
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public String handler(ConstraintViolationException e) {
        StringBuilder strBuilder = new StringBuilder();
        e.getConstraintViolations().stream().forEach(
                violation -> {
                    strBuilder.append(violation.getMessage() + "\n");
                }
        );
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, strBuilder.toString());
    }
}
