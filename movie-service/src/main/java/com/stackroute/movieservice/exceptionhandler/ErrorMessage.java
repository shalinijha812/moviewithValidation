//package com.stackroute.movieservice.exceptionhandler;
//
//import com.stackroute.movieservice.error.ErrorDetails;
//import com.stackroute.movieservice.exception.MovieNotFoundException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import java.util.Date;
//
//
//@ControllerAdvice
//@RestController
//public class ErrorMessage extends ResponseEntityExceptionHandler {
//    @ExceptionHandler(MovieNotFoundException.class)
//    public final ResponseEntity<ErrorDetails> handleUserNotFoundException(MovieNotFoundException ex, WebRequest request) {
//        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
//                request.getDescription(false));
//        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
//    }
//}
