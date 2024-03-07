package com.toob.service.shortest.rest;


import com.toob.service.shortest.model.TechnicalResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


/**
 * And HTTP Error Handler for Our REST API.
 * We pick up Exceptions and map them into a readable standard structure.
 * @author : Thabo Matjuda
 */
@ControllerAdvice
public class RestExceptionHandler {

    /**
     * Bad Request Handler, i.e. When a user has violated the data constraints we have in place.
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(value = {ConstraintViolationException.class, EntityNotFoundException.class})
    public ResponseEntity<TechnicalResponse> resourceNotFoundException(RuntimeException ex, WebRequest request) {
        TechnicalResponse error = TechnicalResponse.error(HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getLocalizedMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
