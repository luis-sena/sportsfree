package br.com.sportsfree.service;

import br.com.sportsfree.dto.EnderecoDto;
import br.com.sportsfree.dto.ProfessorDto;
import br.com.sportsfree.entity.ProfessorEntity;
import br.com.sportsfree.entity.embeddable.Endereco;
import br.com.sportsfree.mapper.ProfessorMapper;
import br.com.sportsfree.repository.ProfessorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProfessorServiceTest {

    @Mock
    private ProfessorRepository repository;
    @Spy
    private ProfessorMapper mapper;

    @InjectMocks
    private ProfessorService service;

    @Test
    @DisplayName("Deve salvar Um professor")
    void deveSalvarUmProfessor() {
        ProfessorDto professorDto = criarProfessorDto();

        when(repository.save(any(ProfessorEntity.class))).thenReturn(criarProfessorEntity());

        ProfessorDto professorSalvo = service.salvar(professorDto);

        verify(repository, times(1)).save(any(ProfessorEntity.class));
        assertThat(professorDto).isEqualTo(professorSalvo);
    }

    @Test
    @DisplayName("Deve atualizar Um professor")
    void deveAtualizarUmProfessor() {
        ProfessorDto professorDto = criarProfessorDtoComId();

        when(repository.save(any(ProfessorEntity.class))).thenReturn(criarProfessorEntity());
        when(repository.findById(anyLong())).thenReturn(Optional.of(criarProfessorEntity()));

        ProfessorDto professorSalvo = service.atualizar(professorDto);

        verify(repository, times(1)).save(any(ProfessorEntity.class));
        verify(repository, times(1)).findById(anyLong());
        assertThat(professorDto).isEqualTo(professorSalvo);
    }

    @Test
    @DisplayName("Deve deletar Um professor")
    void deveDeletarUmProfessor() {
        ProfessorEntity professorEntity = criarProfessorEntityComId();

        doNothing().when(repository).delete(any(ProfessorEntity.class));
        when(repository.findById(anyLong())).thenReturn(Optional.of(criarProfessorEntity()));

        service.deletar(1L);

        verify(repository, times(1)).delete(any(ProfessorEntity.class));
        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Deve recuperar Um professor")
    void deveRecuperarUmProfessor() {
        ProfessorEntity professorEntity = criarProfessorEntity();

        when(repository.findById(anyLong())).thenReturn(Optional.of(criarProfessorEntity()));

        ProfessorDto professorDto = service.recuperar(1L);

        verify(repository, times(1)).findById(anyLong());
        assertThat(mapper.mapToDto(professorEntity)).isEqualTo(professorDto);
    }

    @Test
    @DisplayName("Deve recuperar uma lista de professores")
    void deveRecuperarUmaListaDeProfessores() {
        List<ProfessorEntity> professores = List.of(criarProfessorEntityComId());

        when(repository.findAll()).thenReturn(professores);

        List<ProfessorDto> lista = service.listar();

        verify(repository, times(1)).findAll();
        assertThat(lista).hasSize(1);
    }

    ProfessorDto criarProfessorDto(){
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
    ProfessorEntity criarProfessorEntity(){
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

    ProfessorDto criarProfessorDtoComId(){
        ProfessorDto professorDto = criarProfessorDto();
        professorDto.setId(1L);
        return professorDto;
    }
    ProfessorEntity criarProfessorEntityComId(){
        ProfessorEntity professorEntity = criarProfessorEntity();
        professorEntity.setId(1L);
        return professorEntity;
    }
}