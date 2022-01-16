package br.com.sportsfree.controllers;

import br.com.sportsfree.dto.ProfessorDto;
import br.com.sportsfree.service.impl.ProfessorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("professor")
public class ProfessorController extends AbstractController<ProfessorDto>{

    private final ProfessorService service;

    public ProfessorController(ProfessorService service) {
        super(service);
        this.service = service;
    }
}
