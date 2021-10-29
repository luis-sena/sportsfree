package br.com.sportsfree.utils;

import br.com.sportsfree.dto.EnderecoDto;
import br.com.sportsfree.dto.ProfessorDto;
import br.com.sportsfree.entity.ProfessorEntity;
import br.com.sportsfree.entity.embeddable.Endereco;

public class ProfessorTesteUtils {

    public static ProfessorDto criarProfessorDto(){
        EnderecoDto enderecoDto = EnderecoDto.builder()
                .cep("11111-111")
                .cidade("Cidade do Professor")
                .bairro("Bairro do Professor")
                .rua("Rua do Professor")
                .numero(20)
                .uf("RP")
                .complemento("Complemento do professor")
                .build();

        return ProfessorDto.builder()
                .nome("Professor 1")
                .email("professor@professor.com.br")
                .cpf("111.111.111-11")
                .rg("11.111.11-1")
                .endereco(enderecoDto)
                .build();
    }

    public static ProfessorEntity criarProfessorEntity(){
        Endereco endereco = Endereco.builder()
                .cep("11111-111")
                .cidade("Cidade do Professor")
                .bairro("Bairro do Professor")
                .rua("Rua do Professor")
                .numero(20)
                .uf("RP")
                .complemento("Complemento do professor")
                .build();

        return ProfessorEntity.builder()
                .nome("Professor 1")
                .email("professor@professor.com.br")
                .cpf("111.111.111-11")
                .rg("11.111.11-1")
                .endereco(endereco)
                .build();
    }

    public static ProfessorDto criarProfessorDtoComId(){
        ProfessorDto professorDto = criarProfessorDto();
        professorDto.setId(1L);
        return professorDto;
    }

    public static ProfessorEntity criarProfessorEntityComId(){
        ProfessorEntity professorEntity = criarProfessorEntity();
        professorEntity.setId(1L);
        return professorEntity;
    }
}
