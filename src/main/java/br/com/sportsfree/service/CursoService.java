package br.com.sportsfree.service;

import br.com.sportsfree.dto.CursoDto;
import br.com.sportsfree.entity.CursoEntity;
import br.com.sportsfree.error.RequestParamException;
import br.com.sportsfree.mapper.CursoMapper;
import br.com.sportsfree.repository.CursoRepository;
import org.springframework.stereotype.Service;

@Service
public class CursoService extends AbstractService<CursoDto, CursoEntity> {
    
    private final CursoRepository repository;
    private final CursoMapper mapper;

    public CursoService(CursoRepository repository, CursoMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CursoDto salvar(CursoDto dto) {
        try {
            CursoEntity curso = repository.saveAndFlush(mapper.mapToEntity(dto));
            repository.refresh(curso);
            return mapper.mapToDto(curso);
        } catch (Exception e) {
            throw new RequestParamException("Erro ao salvar recurso, verifique os parametros digitados");
        }
    }
}
