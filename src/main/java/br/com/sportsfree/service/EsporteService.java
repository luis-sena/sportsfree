package br.com.sportsfree.service;

import org.springframework.stereotype.Service;

import br.com.sportsfree.dto.EsporteDto;
import br.com.sportsfree.entity.EsporteEntity;
import br.com.sportsfree.mapper.EsporteMapper;
import br.com.sportsfree.repository.EsporteRepository;

@Service
public class EsporteService extends AbstractService<EsporteDto, EsporteEntity> {
    
    private final EsporteRepository repository;
    private final EsporteMapper mapper;

    public EsporteService(EsporteRepository repository, EsporteMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

}
