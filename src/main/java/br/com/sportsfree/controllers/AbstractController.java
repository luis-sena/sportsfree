package br.com.sportsfree.controllers;

import br.com.sportsfree.dto.AbstractDto;
import br.com.sportsfree.service.AbstractService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractController<D extends AbstractDto> {

    private final AbstractService<D, ?> service;
    
    @GetMapping
    public ResponseEntity<List<D>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("{id}")
    public ResponseEntity<D> recuperar(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.recuperar(id));
    }

    @PostMapping
    public ResponseEntity<D> salvar(@Valid @RequestBody D dto) {
        return ResponseEntity.accepted().body(service.salvar(dto));
    }

    @PutMapping
    public ResponseEntity<D> atualizar(@RequestBody D dto) {
        return ResponseEntity.ok(service.atualizar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable("id") Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
