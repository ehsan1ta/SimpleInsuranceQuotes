package com.example.siqa.utils;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.stream.Collectors;

@Service
public class BindingResultUtil {

    public String getErrors(BindingResult bindingResult){
        String errorMessage = null;
        if (bindingResult.hasErrors()) {
            // Handle validation errors
            errorMessage = bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
        }
        return errorMessage;
    }
}
