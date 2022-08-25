package br.com.manager.tasks.controllers;

import br.com.manager.tasks.entities.Clients;
import br.com.manager.tasks.repositories.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin("*")
public class ClientsController {

    @Autowired
    private ClientsRepository repository;

    @GetMapping
    public List<Clients> index() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Clients view( @PathVariable Integer id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Clients add( @RequestBody @Valid Clients client ) {
        return repository.save(client);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update( @PathVariable Integer id, @RequestBody @Valid Clients client_update) {
        repository
                .findById(id)
                .map(clients -> {
                    client_update.setId(clients.getId());
                    return repository.save(client_update);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable Integer id ) {
        repository
                .findById(id)
                .map(clients -> {
                    repository.delete(clients);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!"));
    }
}
