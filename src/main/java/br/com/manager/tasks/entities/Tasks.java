package br.com.manager.tasks.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Integer id;

    @Column(length = 220, nullable = false)
    @NotEmpty(message = "{field.request.required}")
    private String request;

    @Column(length = 220, nullable = false)
    @NotEmpty(message = "{field.responsible.required}")
    private String responsible;

    @Column(nullable = false)
    @NotEmpty(message = "{field.description.required}")
    private String description;

    @Column(length = 30, nullable = false)
    @NotEmpty(message = "{field.priority.required}")
    private String priority;

    @Column(nullable = false)
    @NotEmpty(message = "{field.deadline.required}")
    private String deadline;

    @ManyToOne
    @JoinColumn(name = "clients_id")
    private Clients clients;
}
