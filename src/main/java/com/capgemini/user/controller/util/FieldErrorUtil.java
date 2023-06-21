package com.capgemini.user.controller.util;

import com.capgemini.user.service.exception.UserBadRequestException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Component
public class FieldErrorUtil {

    public void checkErrors(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();
        bindingResult.getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        if (bindingResult.hasErrors()) {
            throw new UserBadRequestException(errors);
        }
    }


}
