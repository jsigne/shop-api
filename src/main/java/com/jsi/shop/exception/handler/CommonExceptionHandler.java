package com.jsi.shop.exception.handler;

import com.jsi.shop.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler
    public ProblemDetail handleNotFoundException(NotFoundException notFoundException){
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, notFoundException.getMessage());
    }

}
