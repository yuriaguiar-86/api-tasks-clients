package br.com.manager.tasks.controllers;

import br.com.manager.tasks.exceptions.ErrorsApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsApi handleValidationErrors(MethodArgumentNotValidException exception) {
         BindingResult bindingResult = exception.getBindingResult();
         List<String> messages = bindingResult.getAllErrors()
                 .stream()
                 .map(objectError -> objectError.getDefaultMessage())
                 .collect(Collectors.toList());

         return new ErrorsApi(messages);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity handleResponseException(ResponseStatusException exception) {
        String error = exception.getMessage();
        HttpStatus status = exception.getStatus();
        ErrorsApi errorsApi = new ErrorsApi(error);

        return new ResponseEntity(errorsApi, status);
    }
}
