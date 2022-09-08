package br.com.manager.tasks.controllers.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class TasksDTO {
    @NotEmpty(message = "{field.request.required}")
    private String request;

    @NotEmpty(message = "{field.responsible.required}")
    private String responsible;

    @NotEmpty(message = "{field.description.required}")
    private String description;

    @NotEmpty(message = "{field.priority.required}")
    private String priority;

    @NotEmpty(message = "{field.deadline.required}")
    private String deadline;

    @NotNull(message = "{field.client.required}")
    private Integer client_id;
}
