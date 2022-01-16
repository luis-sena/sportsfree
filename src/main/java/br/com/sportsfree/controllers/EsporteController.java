package br.com.sportsfree.controllers;

import br.com.sportsfree.dto.EsporteDto;
import br.com.sportsfree.service.impl.EsporteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("esporte")
public class EsporteController extends AbstractController<EsporteDto>{
    
    private final EsporteService service;

    public EsporteController(EsporteService service) {
        super(service);
        this.service = service;
    }

}
