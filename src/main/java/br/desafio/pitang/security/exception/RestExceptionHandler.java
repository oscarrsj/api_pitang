package br.desafio.pitang.security.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	        List<ErrorObject> errors = getErrors(ex);
	        ErrorResponse errorResponse = getErrorResponse(ex, status, errors);
	        return new ResponseEntity<>(errorResponse, status);
	    }
		
	    private ErrorResponse getErrorResponse(MethodArgumentNotValidException ex, HttpStatus status, List<ErrorObject> errors) {
	        return new ErrorResponse("Invalid Fields", status.value());
	    }
	    
	    
	    private List<ErrorObject> getErrors(MethodArgumentNotValidException ex) {
	        return ex.getBindingResult().getFieldErrors().stream()
	                .map(error -> new ErrorObject(error.getDefaultMessage(), error.getField(), error.getRejectedValue()))
	                .collect(Collectors.toList());
	    }
    
    
    @ExceptionHandler(InvalidFieldsException.class)
    public ResponseEntity<?> handleInvalidFieldsException(InvalidFieldsException exception) {
            return new ResponseEntity<>(new ErrorResponse(exception.getMessage() , HttpStatus.CONFLICT.value()), HttpStatus.CONFLICT);
    }
    
    
    @ExceptionHandler(ResourceUnAuthorizedException.class)
    public ResponseEntity<?> handleResourceUnAuthorizedExceptionn(ResourceUnAuthorizedException exception) {
            return new ResponseEntity<>(new ErrorResponse(exception.getMessage() , HttpStatus.UNAUTHORIZED.value()), HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<?> handleInvalidTokenException(ResourceUnAuthorizedException exception) {
    	return new ResponseEntity<>(new ErrorResponse(exception.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

	
}
