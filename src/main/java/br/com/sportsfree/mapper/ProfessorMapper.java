package br.com.sportsfree.mapper;

import br.com.sportsfree.dto.EnderecoDto;
import br.com.sportsfree.dto.ProfessorDto;
import br.com.sportsfree.entity.embeddable.Endereco;
import br.com.sportsfree.entity.ProfessorEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProfessorMapper implements AbstractMapper<ProfessorDto, ProfessorEntity> {

    public ProfessorEntity mapToEntity(ProfessorDto dto) {
        ProfessorEntity professor = new ProfessorEntity();
        Endereco endereco = new Endereco();
        BeanUtils.copyProperties(dto, professor);
        BeanUtils.copyProperties(dto.getEndereco(), endereco);
        professor.setEndereco(endereco);
        professor.setId(dto.getId());
        return professor;
    }

    public ProfessorDto mapToDto(ProfessorEntity professor) {
        ProfessorDto dto = new ProfessorDto();
        EnderecoDto enderecoDto = new EnderecoDto();
        BeanUtils.copyProperties(professor, dto);
        BeanUtils.copyProperties(professor.getEndereco(), enderecoDto);
        dto.setEndereco(enderecoDto);
        dto.setId(professor.getId());
        return dto;
    }
}
