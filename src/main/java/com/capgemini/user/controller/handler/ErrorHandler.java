package com.capgemini.user.controller.handler;

import com.capgemini.user.controller.model.ErrorModelApi;
import com.capgemini.user.service.exception.UserBadRequestException;
import com.capgemini.user.service.exception.UserNotFoundException;
import com.capgemini.user.service.exception.UserUnexpectedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorModelApi userNotFound(UserNotFoundException userNotFoundException) {
        return ErrorModelApi.builder()
                .code("2")
                .error(userNotFoundException.getMessage())
                .build();
    }
    @ExceptionHandler(UserBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorModelApi badRequestException(UserBadRequestException userBadRequestException) {
        return ErrorModelApi.builder()
                .code("4")
                .errors(userBadRequestException.getErrors())
                .build();
    }


    @ExceptionHandler(UserUnexpectedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorModelApi unexpectedException(UserUnexpectedException userUnexpectedException) {
        return ErrorModelApi.builder()
                .code("-1")
                .error(userUnexpectedException.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorModelApi genericException(Exception exception) {
        return ErrorModelApi.builder()
                .code("5")
                .error(exception.getMessage())
                .build();
    }
}
