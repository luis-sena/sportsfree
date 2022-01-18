package br.com.sportsfree.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.sportsfree.dto.CursoDto;
import br.com.sportsfree.dto.EnderecoDto;
import br.com.sportsfree.dto.EsporteDto;
import br.com.sportsfree.dto.ProfessorDto;
import br.com.sportsfree.entity.CursoEntity;
import br.com.sportsfree.entity.EsporteEntity;
import br.com.sportsfree.entity.ProfessorEntity;
import br.com.sportsfree.entity.embeddable.Endereco;

@Component
public class CursoMapper implements AbstractMapper<CursoDto, CursoEntity>{
    
    public CursoEntity mapToEntity(CursoDto dto) {
        CursoEntity curso = new CursoEntity();       
        BeanUtils.copyProperties(dto, curso);
        
        EsporteEntity esporte = new EsporteEntity();
        ProfessorEntity professor = new ProfessorEntity();
        Endereco endereco = new Endereco();

        BeanUtils.copyProperties(dto.getEsporte(), esporte);
        BeanUtils.copyProperties(dto.getProfessor(), professor);
        BeanUtils.copyProperties(dto.getLocal(), endereco);
       
        curso.setEsporte(esporte);
        curso.setProfessor(professor);
        curso.setLocal(endereco);        
        
        curso.setId(dto.getId());
        return curso;
    }

    public CursoDto mapToDto(CursoEntity curso) {
        CursoDto dto = new CursoDto();
        BeanUtils.copyProperties(curso, dto);

        EsporteDto esporteDto = new EsporteDto();
        ProfessorDto professorDto = new ProfessorDto();
        EnderecoDto professorEnderecoDto = new EnderecoDto();
        EnderecoDto enderecoDto = new EnderecoDto();

        esporteDto.setId(curso.getEsporte().getId());
        professorDto.setId(curso.getProfessor().getId());
               
        BeanUtils.copyProperties(curso.getEsporte(), esporteDto);
        BeanUtils.copyProperties(curso.getProfessor(), professorDto);
        BeanUtils.copyProperties(curso.getLocal(), enderecoDto);

        if(curso.getProfessor().getEndereco() != null) {
            BeanUtils.copyProperties(curso.getProfessor().getEndereco(), professorEnderecoDto);
            professorDto.setEndereco(professorEnderecoDto);
        }        

        dto.setEsporte(esporteDto);
        dto.setProfessor(professorDto);
        dto.setLocal(enderecoDto);

        dto.setId(curso.getId());
        return dto;
    }

}
