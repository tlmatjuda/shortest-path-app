package za.co.discovery.assignment.thabomatjuda.rest;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import za.co.discovery.assignment.thabomatjuda.model.BaseResponse;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<BaseResponse> resourceNotFoundException(RuntimeException ex, WebRequest request) {
        BaseResponse error = BaseResponse.error(HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getLocalizedMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
