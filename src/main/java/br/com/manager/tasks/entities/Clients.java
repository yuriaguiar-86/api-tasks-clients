package br.com.manager.tasks.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Clients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Integer id;

    @Column(length = 220, nullable = false)
    @NotEmpty(message = "{field.name.required}")
    private String name;

    @Column(length = 20, nullable = false, unique = true)
    @NotNull(message = "{field.cnpj.required}")
    @CNPJ(message = "{field.cnpj.required}")
    private String cnpj;
}
