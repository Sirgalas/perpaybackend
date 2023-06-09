package ru.sergalas.perpay.security.validators;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.Map;

@Component
public class ResponseErrorValidator {
    public ResponseEntity<Object> responseMapValidator(BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            if((!CollectionUtils.isEmpty(bindingResult.getAllErrors()))) {
                for(ObjectError error: bindingResult.getAllErrors()) {
                    errorMap.put(error.getCode(), error.getDefaultMessage());
                }
            }
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errorMap.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}
