package br.com.sportsfree.service;

import br.com.sportsfree.dto.DoadorDto;
import br.com.sportsfree.entity.DoadorEntity;
import br.com.sportsfree.mapper.DoadorMapper;
import br.com.sportsfree.repository.DoadorRepository;
import org.springframework.stereotype.Service;

@Service
public class DoadorService extends AbstractService<DoadorDto, DoadorEntity> {

    private final DoadorRepository repository;
    private final DoadorMapper mapper;

    public DoadorService(DoadorRepository repository, DoadorMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }
}
