package br.desafio.pitang.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    
    
    @ExceptionHandler(InvalidFieldsException.class)
    public ResponseEntity<?> handleResourceNotFoundException(InvalidFieldsException exception) {
            return new ResponseEntity<>(new ErrorResponse(exception.getMessage() , HttpStatus.CONFLICT.value()), HttpStatus.CONFLICT);
    }

	
}
