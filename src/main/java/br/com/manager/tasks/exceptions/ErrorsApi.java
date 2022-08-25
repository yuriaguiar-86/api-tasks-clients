package br.com.manager.tasks.exceptions;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ErrorsApi {
    @Getter
    private List<String> errors;

    public ErrorsApi(List<String> errors) {
        this.errors = errors;
    }

    public ErrorsApi(String message) {
        this.errors = Arrays.asList(message);
    }
}
