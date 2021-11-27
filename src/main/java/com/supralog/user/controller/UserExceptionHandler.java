package com.supralog.user.controller;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.supralog.user.commons.UserNotFoundException;
/**
 * <b>RestController for Exceptions</b>
 * <p>
 * 
 * Format Exception into JSON response
 * 
 * @author F.Anelli
 *
 */
@RestControllerAdvice
public class UserExceptionHandler {

	/**
	 * 
	 * @param e UserNotFoundException catch by GetMapping when user isn't found
	 * @return Error 404 with pseudo 
	 */
	@ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleException(UserNotFoundException e) {
        return ResponseEntity
            .status(e.getStatus())
            .body(new ExceptionResponse(e.getMessage(), e.getStatus().value()));
    }

	/**
	 * 
	 * @param e MissingServletRequestParameterException Exception catch by PostMapping missing requestparam
	 * @return Error 404 with missing value
	 */
	@ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ExceptionResponse> handleException(MissingServletRequestParameterException e) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(new ExceptionResponse(e.getMessage(),HttpStatus.NOT_FOUND.value()));
    }
	
/**
 * 
 * @param e ConstraintViolationException Exception catch by Entity User
 * @return Error 404 with List of violation exception
 */
	@ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> handleException(ConstraintViolationException e) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(new ExceptionResponse(e.getMessage(),HttpStatus.NOT_FOUND.value()));
    }
	
	
    public static class ExceptionResponse {

        private final String message;
        public String getMessage() { return message; }

        private final Integer code;
        public Integer getCode() { return code; }

        public ExceptionResponse(String message, Integer code) {
            this.message = message;
            this.code = code;
        }

    }

}
