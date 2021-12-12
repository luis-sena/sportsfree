package br.com.sportsfree.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sportsfree.dto.CursoDto;
import br.com.sportsfree.dto.EsporteDto;
import br.com.sportsfree.dto.ProfessorDto;
import br.com.sportsfree.entity.CursoEntity;
import br.com.sportsfree.mapper.CursoMapper;
import br.com.sportsfree.repository.CursoRepository;

@Service
public class CursoService extends AbstractService<CursoDto, CursoEntity> {
    
    private final CursoRepository repository;
    private final CursoMapper mapper;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private EsporteService esporteService;

    public CursoService(CursoRepository repository, CursoMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CursoDto salvar(CursoDto dto) {
       return super.salvar(this.carregarObjetos(dto));
    }

    @Override
    public CursoDto atualizar(CursoDto dto) {
        return super.atualizar(this.carregarObjetos(dto));
    }

    private CursoDto carregarObjetos(CursoDto dto){

        if(this.professorService != null) {
            ProfessorDto professorDto = this.professorService.recuperar(dto.getProfessor().getId());
            dto.setProfessor(professorDto);
        }
        
        if(this.esporteService != null) {
            EsporteDto esporteDto = this.esporteService.recuperar(dto.getEsporte().getId());
            dto.setEsporte(esporteDto);
        }
        
        return dto;
    }

}
