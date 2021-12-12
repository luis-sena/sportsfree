package br.com.sportsfree.mapper;

import static br.com.sportsfree.utils.CursoTesteUtils.criarCursoEntityComId;
import static br.com.sportsfree.utils.CursoTesteUtils.criarCursoDtoComId;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.sportsfree.dto.CursoDto;
import br.com.sportsfree.dto.EnderecoDto;
import br.com.sportsfree.dto.EsporteDto;
import br.com.sportsfree.dto.ProfessorDto;
import br.com.sportsfree.entity.CursoEntity;
import br.com.sportsfree.entity.EsporteEntity;
import br.com.sportsfree.entity.ProfessorEntity;
import br.com.sportsfree.entity.embeddable.Endereco;

class CursoMapperTest {
    private CursoMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new CursoMapper();
    }

    @Test
    @DisplayName("Deve Mapear Um Entity Para Dto")
    void deveMapearUmEntityParaDto() {
        CursoEntity cursoEntity = criarCursoEntityComId();

        CursoDto cursoDto = mapper.mapToDto(cursoEntity);

        EnderecoDto enderecoDto = cursoDto.getLocal();
        Endereco endereco = cursoEntity.getLocal();

        ProfessorDto professorDto = cursoDto.getProfessor();
        ProfessorEntity professorEntity = cursoEntity.getProfessor();

        EsporteDto esporteDto = cursoDto.getEsporte();
        EsporteEntity esporteEntity = cursoEntity.getEsporte();

        assertThat(cursoDto).isNotNull();
        assertThat(cursoDto.getId()).isNotNull();
        assertThat(cursoDto.getDescricao()).isEqualTo(cursoEntity.getDescricao());
        assertThat(cursoDto.getDia()).isEqualTo(cursoEntity.getDia());
        assertThat(cursoDto.getHorario_inicio()).isEqualTo(cursoEntity.getHorario_inicio());
        assertThat(cursoDto.getHorario_fim()).isEqualTo(cursoEntity.getHorario_fim());
        assertThat(cursoDto.getQuantidade_max_alunos()).isEqualTo(cursoEntity.getQuantidade_max_alunos());

        assertThat(professorDto).isNotNull();
        assertThat(professorDto.getId()).isNotNull();
        assertThat(professorDto.getNome()).isEqualTo(professorEntity.getNome());
        assertThat(professorDto.getEmail()).isEqualTo(professorEntity.getEmail());
        assertThat(professorDto.getCpf()).isEqualTo(professorEntity.getCpf());
        assertThat(professorDto.getRg()).isEqualTo(professorEntity.getRg());

        assertThat(esporteDto).isNotNull();
        assertThat(esporteDto.getId()).isNotNull();        
        assertThat(esporteDto.getNome()).isEqualTo(esporteEntity.getNome());
        assertThat(esporteDto.getDescricao()).isEqualTo(esporteEntity.getDescricao());
        assertThat(esporteDto.getUrlImagem()).isEqualTo(esporteEntity.getUrlImagem());

        assertThat(enderecoDto.getRua()).isEqualTo(endereco.getRua());
        assertThat(enderecoDto.getNumero()).isEqualTo(endereco.getNumero());
        assertThat(enderecoDto.getBairro()).isEqualTo(endereco.getBairro());
        assertThat(enderecoDto.getCidade()).isEqualTo(endereco.getCidade());
        assertThat(enderecoDto.getCep()).isEqualTo(endereco.getCep());
        assertThat(enderecoDto.getComplemento()).isEqualTo(endereco.getComplemento());
        assertThat(enderecoDto.getUf()).isEqualTo(endereco.getUf());
        
    }

    @Test
    @DisplayName("Deve Mapear Um Dto Para Entity")
    void deveMapearUmDtoParaEntity() {
        CursoDto cursoDto = criarCursoDtoComId();
        CursoEntity curso = mapper.mapToEntity(cursoDto);

        EnderecoDto enderecoDto = cursoDto.getLocal();
        Endereco endereco = curso.getLocal();

        ProfessorDto professorDto = cursoDto.getProfessor();
        ProfessorEntity professorEntity = curso.getProfessor();

        EsporteDto esporteDto = cursoDto.getEsporte();
        EsporteEntity esporteEntity = curso.getEsporte();

        assertThat(curso).isNotNull();
        assertThat(curso.getId()).isNotNull();
        assertThat(curso.getDescricao()).isEqualTo(cursoDto.getDescricao());
        assertThat(curso.getDia()).isEqualTo(cursoDto.getDia());
        assertThat(curso.getHorario_inicio()).isEqualTo(cursoDto.getHorario_inicio());
        assertThat(curso.getHorario_fim()).isEqualTo(cursoDto.getHorario_fim());
        assertThat(curso.getQuantidade_max_alunos()).isEqualTo(cursoDto.getQuantidade_max_alunos());

        assertThat(professorEntity).isNotNull();
        assertThat(professorEntity.getId()).isNotNull();
        assertThat(professorEntity.getNome()).isEqualTo(professorDto.getNome());
        assertThat(professorEntity.getEmail()).isEqualTo(professorDto.getEmail());
        assertThat(professorEntity.getCpf()).isEqualTo(professorDto.getCpf());
        assertThat(professorEntity.getRg()).isEqualTo(professorDto.getRg());

        assertThat(esporteEntity).isNotNull();
        assertThat(esporteEntity.getId()).isNotNull();        
        assertThat(esporteEntity.getNome()).isEqualTo(esporteDto.getNome());
        assertThat(esporteEntity.getDescricao()).isEqualTo(esporteDto.getDescricao());
        assertThat(esporteEntity.getUrlImagem()).isEqualTo(esporteDto.getUrlImagem());

        assertThat(endereco.getRua()).isEqualTo(enderecoDto.getRua());
        assertThat(endereco.getNumero()).isEqualTo(enderecoDto.getNumero());
        assertThat(endereco.getBairro()).isEqualTo(enderecoDto.getBairro());
        assertThat(endereco.getCidade()).isEqualTo(enderecoDto.getCidade());
        assertThat(endereco.getCep()).isEqualTo(enderecoDto.getCep());
        assertThat(endereco.getComplemento()).isEqualTo(enderecoDto.getComplemento());
        assertThat(endereco.getUf()).isEqualTo(enderecoDto.getUf());
        
    }
}