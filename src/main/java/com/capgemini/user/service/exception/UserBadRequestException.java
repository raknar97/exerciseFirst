package com.capgemini.user.service.exception;

import lombok.Getter;

import java.util.Map;

public class UserBadRequestException extends RuntimeException {
    @Getter
    private Map<String, String> errors;

    public UserBadRequestException(Map<String, String> errors) {
        this.errors = errors;
    }




}
