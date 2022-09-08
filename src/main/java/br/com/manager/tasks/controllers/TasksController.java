package br.com.manager.tasks.controllers;

import br.com.manager.tasks.controllers.dto.TasksDTO;
import br.com.manager.tasks.entities.Clients;
import br.com.manager.tasks.entities.Tasks;
import br.com.manager.tasks.repositories.ClientsRepository;
import br.com.manager.tasks.repositories.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TasksController {

    @Autowired
    private TasksRepository repository;
    @Autowired
    private ClientsRepository clientsRepository;

    @GetMapping
    public List<Tasks> index() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Tasks view( @PathVariable Integer id ) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found!"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tasks add( @RequestBody @Valid TasksDTO dto ) {
        Integer client_id = dto.getClient_id();
        Clients client = clientsRepository
                .findById(client_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Task not exist!"));

        Tasks tasks = new Tasks();
        tasks.setDescription(dto.getDescription());
        tasks.setDeadline(dto.getDeadline());
        tasks.setRequest(dto.getRequest());
        tasks.setPriority(dto.getPriority());
        tasks.setResponsible(dto.getResponsible());
        tasks.setClients(client);

        return repository.save(tasks);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update( @PathVariable Integer id, @RequestBody @Valid Tasks task_update) {
        repository
                .findById(id)
                .map(tasks -> {
                    task_update.setId(tasks.getId());
                    return repository.save(task_update);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found!"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable Integer id ) {
        repository
                .findById(id)
                .map(tasks -> {
                    repository.delete(tasks);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found!"));
    }
}
