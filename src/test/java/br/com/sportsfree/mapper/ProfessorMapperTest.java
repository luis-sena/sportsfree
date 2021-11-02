package br.com.sportsfree.mapper;

import br.com.sportsfree.dto.EnderecoDto;
import br.com.sportsfree.dto.ProfessorDto;
import br.com.sportsfree.entity.ProfessorEntity;
import br.com.sportsfree.entity.embeddable.Endereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.com.sportsfree.utils.ProfessorTesteUtils.criarProfessorDtoComId;
import static br.com.sportsfree.utils.ProfessorTesteUtils.criarProfessorEntityComId;
import static org.assertj.core.api.Assertions.assertThat;

class ProfessorMapperTest {
    private ProfessorMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ProfessorMapper();
    }

    @Test
    @DisplayName("Deve Mapear Um Entity Para Dto")
    void deveMapearUmEntityParaDto() {
        ProfessorEntity professorEntity = criarProfessorEntityComId();

        ProfessorDto professorDto = mapper.mapToDto(professorEntity);

        EnderecoDto enderecoDto = professorDto.getEndereco();
        Endereco endereco = professorEntity.getEndereco();

        assertThat(professorDto).isNotNull();
        assertThat(professorDto.getId()).isNotNull();
        assertThat(professorDto.getNome()).isEqualTo(professorEntity.getNome());
        assertThat(professorDto.getEmail()).isEqualTo(professorEntity.getEmail());
        assertThat(professorDto.getCpf()).isEqualTo(professorEntity.getCpf());
        assertThat(professorDto.getRg()).isEqualTo(professorEntity.getRg());

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
        ProfessorDto professorDto = criarProfessorDtoComId();

        ProfessorEntity professor = mapper.mapToEntity(professorDto);

        Endereco endereco = professor.getEndereco();
        EnderecoDto enderecoDto = professorDto.getEndereco();

        assertThat(professor).isNotNull();
        assertThat(professor.getId()).isNotNull();
        assertThat(professor.getNome()).isEqualTo(professorDto.getNome());
        assertThat(professor.getEmail()).isEqualTo(professorDto.getEmail());
        assertThat(professor.getCpf()).isEqualTo(professorDto.getCpf());
        assertThat(professor.getRg()).isEqualTo(professorDto.getRg());

        assertThat(endereco.getRua()).isEqualTo(enderecoDto.getRua());
        assertThat(endereco.getNumero()).isEqualTo(enderecoDto.getNumero());
        assertThat(endereco.getBairro()).isEqualTo(enderecoDto.getBairro());
        assertThat(endereco.getCidade()).isEqualTo(enderecoDto.getCidade());
        assertThat(endereco.getCep()).isEqualTo(enderecoDto.getCep());
        assertThat(endereco.getComplemento()).isEqualTo(enderecoDto.getComplemento());
        assertThat(endereco.getUf()).isEqualTo(enderecoDto.getUf());
    }
}