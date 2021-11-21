package br.com.sportsfree.mapper;

import br.com.sportsfree.dto.ContatoDto;
import br.com.sportsfree.dto.DoadorDto;
import br.com.sportsfree.dto.EnderecoDto;
import br.com.sportsfree.entity.DoadorEntity;
import br.com.sportsfree.entity.embeddable.Contato;
import br.com.sportsfree.entity.embeddable.Endereco;
import br.com.sportsfree.utils.DoadorTesteUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.com.sportsfree.utils.DoadorTesteUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

class DoadorMapperTest {

    private DoadorMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new DoadorMapper();
    }

    @Test
    @DisplayName("Deve Mapear Um Entity Para Dto")
    void deveMapearUmEntityParaDto() {
        DoadorEntity doadorEntity = criarDoadorEntityComId();

        DoadorDto doadorDto = mapper.mapToDto(doadorEntity);

        ContatoDto contatoDto = doadorDto.getContato();
        Contato contato = doadorEntity.getContato();

        EnderecoDto enderecoDto = doadorDto.getEndereco();
        Endereco endereco = doadorEntity.getEndereco();

        assertThat(doadorDto).isNotNull();
        assertThat(doadorDto.getId()).isNotNull();
        assertThat(doadorDto.getNome()).isEqualTo(doadorEntity.getNome());
        assertThat(doadorDto.getCpfCnpj()).isEqualTo(doadorEntity.getCpfCnpj());
        assertThat(doadorDto.getRg()).isEqualTo(doadorEntity.getRg());

        assertThat(contatoDto.getEmail()).isEqualTo(contato.getEmail());
        assertThat(contatoDto.getTelefones()).isEqualTo(contato.getTelefones());
        assertThat(contatoDto.getRedeSocial()).isEqualTo(contato.getRedeSocial());

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
        DoadorDto doadorDto = criarDoadorDtoComId();

        DoadorEntity doador = mapper.mapToEntity(doadorDto);

        Contato contato = doador.getContato();
        ContatoDto contatoDto = doadorDto.getContato();

        Endereco endereco = doador.getEndereco();
        EnderecoDto enderecoDto = doadorDto.getEndereco();

        assertThat(doador).isNotNull();
        assertThat(doador.getId()).isNotNull();
        assertThat(doador.getNome()).isEqualTo(doadorDto.getNome());
        assertThat(doador.getCpfCnpj()).isEqualTo(doadorDto.getCpfCnpj());
        assertThat(doador.getRg()).isEqualTo(doadorDto.getRg());

        assertThat(contato.getEmail()).isEqualTo(contatoDto.getEmail());
        assertThat(contato.getTelefones()).isEqualTo(contatoDto.getTelefones());
        assertThat(contato.getRedeSocial()).isEqualTo(contatoDto.getRedeSocial());

        assertThat(endereco.getRua()).isEqualTo(enderecoDto.getRua());
        assertThat(endereco.getNumero()).isEqualTo(enderecoDto.getNumero());
        assertThat(endereco.getBairro()).isEqualTo(enderecoDto.getBairro());
        assertThat(endereco.getCidade()).isEqualTo(enderecoDto.getCidade());
        assertThat(endereco.getCep()).isEqualTo(enderecoDto.getCep());
        assertThat(endereco.getComplemento()).isEqualTo(enderecoDto.getComplemento());
        assertThat(endereco.getUf()).isEqualTo(enderecoDto.getUf());
    }
}