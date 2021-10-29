package br.com.sportsfree.service;

import br.com.sportsfree.dto.ProfessorDto;
import br.com.sportsfree.entity.ProfessorEntity;
import br.com.sportsfree.mapper.ProfessorMapper;
import br.com.sportsfree.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService extends AbstractService<ProfessorDto, ProfessorEntity> {

    private final ProfessorRepository repository;
    private final ProfessorMapper mapper;

    public ProfessorService(ProfessorRepository repository, ProfessorMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }
}
