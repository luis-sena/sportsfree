package br.com.lsena.sportsfree.controllers;

import br.com.lsena.sportsfree.entity.Professor;
import br.com.lsena.sportsfree.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("professor")
public class ProfessorController {

    private final ProfessorRepository repository;

    @GetMapping
    public ResponseEntity<List<Professor>> listar(){
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    public ResponseEntity<Professor> salvar(Professor professor){
        return ResponseEntity.ok(repository.save(professor));
    }
}
