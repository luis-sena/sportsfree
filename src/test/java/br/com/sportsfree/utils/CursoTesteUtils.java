package br.com.sportsfree.utils;

import br.com.sportsfree.dto.CursoDto;
import br.com.sportsfree.dto.EnderecoDto;
import br.com.sportsfree.dto.EsporteDto;
import br.com.sportsfree.dto.ProfessorDto;
import br.com.sportsfree.entity.CursoEntity;
import br.com.sportsfree.entity.EsporteEntity;
import br.com.sportsfree.entity.ProfessorEntity;
import br.com.sportsfree.entity.embeddable.Endereco;

import java.time.LocalTime;

public class CursoTesteUtils {
    
    public static CursoDto criarCursoDto(){

        EnderecoDto enderecoDto = EnderecoDto.builder()
            .cep("11111-111")
            .cidade("Cidade do Professor")
            .bairro("Bairro do Professor")
            .rua("Rua do Professor")
            .numero(20)
            .uf("RP")
            .complemento("Complemento do professor")
            .build();

        EsporteDto esporteDto =  EsporteDto.builder()
            .id(1L)
            .nome("Esporte 01")
            .descricao("Descrição sobre o esporte")
            .urlImagem("https://localimagem.com")
            .build();

        ProfessorDto professorDto = ProfessorDto.builder()
            .id(1L)
            .nome("Professor 1")
            .email("professor@professor.com.br")
            .cpf("111.111.111-11")
            .rg("11.111.11-1")
            .endereco(enderecoDto)
            .build();
        
        return CursoDto.builder()
            .descricao("Curso de Futsal")
            .professor(professorDto)
            .esporte(esporteDto)
            .local(enderecoDto)
            .dia("Domingo")
            .horarioInicio(LocalTime.of(8,0))
            .horarioFim(LocalTime.of(10,0))
            .quantidadeMaxAlunos(20)
            .build();
    }

    public static CursoEntity criarCursoEntity(){
        
        Endereco endereco = Endereco.builder()
            .cep("11111-111")
            .cidade("Cidade do Professor")
            .bairro("Bairro do Professor")
            .rua("Rua do Professor")
            .numero(20)
            .uf("RP")
            .complemento("Complemento do professor")
            .build();
        
        EsporteEntity esporteEntity = EsporteEntity.builder()
            .id(1L)
            .nome("Esporte 01")
            .descricao("Descrição sobre o esporte")
            .urlImagem("https://localimagem.com")
            .build();

        ProfessorEntity professorEntity = ProfessorEntity.builder()
            .id(1L)
            .nome("Professor 1")
            .email("professor@professor.com.br")
            .cpf("111.111.111-11")
            .rg("11.111.11-1")
            .endereco(endereco)
            .build();

        return CursoEntity.builder()
                .descricao("Curso de Futsal")
                .professor(professorEntity)
                .esporte(esporteEntity)
                .local(endereco)
                .dia("Domingo")
                .horarioInicio(LocalTime.of(8,0))
                .horarioFim(LocalTime.of(10,0))
                .quantidadeMaxAlunos(20)
                .build();
    }

    public static CursoDto criarCursoDtoComId(){
        CursoDto cursoDto = criarCursoDto();
        cursoDto.setId(1L);
        return cursoDto;
    }

    public static CursoEntity criarCursoEntityComId(){
        CursoEntity cursoEntity = criarCursoEntity();
        cursoEntity.setId(1L);
        return cursoEntity;
    }

}
