package br.com.manager.tasks.controllers.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TasksDTO {
    private String request;
    private String responsible;
    private String description;
    private String priority;
    private String deadline;
    private Integer client_id;
}
