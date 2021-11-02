package br.com.sportsfree.controllers;

import br.com.sportsfree.dto.DoadorDto;
import br.com.sportsfree.service.DoadorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("doador")
public class DoadorController extends AbstractController<DoadorDto>{

    private final DoadorService service;

    public DoadorController(DoadorService service) {
        super(service);
        this.service = service;
    }
}
