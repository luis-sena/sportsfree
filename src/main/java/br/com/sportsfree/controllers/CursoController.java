package br.com.sportsfree.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sportsfree.dto.CursoDto;
import br.com.sportsfree.service.CursoService;

@RestController
@RequestMapping("curso")
public class CursoController extends AbstractController<CursoDto>{
    
    private final CursoService service;

    public CursoController(CursoService service) {
        super(service);
        this.service = service;
    }

}
