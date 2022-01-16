
package br.com.sportsfree.service;

import br.com.sportsfree.dto.ProfessorDto;
import br.com.sportsfree.dto.UsuarioDto;
import br.com.sportsfree.entity.ProfessorEntity;
import br.com.sportsfree.error.RequestParamException;
import br.com.sportsfree.error.ResourceNotFoundExeption;
import br.com.sportsfree.mapper.ProfessorMapper;
import br.com.sportsfree.repository.ProfessorRepository;
import br.com.sportsfree.service.impl.ProfessorService;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuthException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static br.com.sportsfree.utils.ProfessorTesteUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ProfessorServiceTest {

    @Mock
    private ProfessorRepository repository;
    @Spy
    private ProfessorMapper mapper;
    @Mock
    private UsuarioService usuarioService;
    @InjectMocks
    private ProfessorService service;

    @Test
    @DisplayName("Deve salvar Um professor")
    void deveSalvarUmProfessor() throws Exception {
        ProfessorDto professorDto = criarProfessorDto();

        when(repository.save(any(ProfessorEntity.class))).thenReturn(criarProfessorEntity());
        doNothing().when(usuarioService).cadastro(any(UsuarioDto.class));

        ProfessorDto professorSalvo = service.salvar(professorDto);

        verify(repository, times(1)).save(any(ProfessorEntity.class));
        assertThat(professorDto).isEqualTo(professorSalvo);
    }

    @Test
    @DisplayName("Deve Lancar RequestParamException Quando Não Salvar Um Professor")
    void deveLancarRequestParamExceptionQuandoNaoSalvarUmProfessor() throws Exception {
        ProfessorDto professorDto = criarProfessorDto();

        when(repository.save(any(ProfessorEntity.class))).thenThrow(RuntimeException.class);
        doNothing().when(usuarioService).cadastro(any(UsuarioDto.class));

        assertThatExceptionOfType(RequestParamException.class)
                .isThrownBy(() -> service.salvar(professorDto))
                .withMessage("Erro ao salvar recurso, verifique os parametros digitados");
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
    @DisplayName("Deve Lancar RequestParamException Quando Não Atualizar Um Professor")
    void deveLancarRequestParamExceptionQuandoNaoAtualizarUmProfessor() {
        ProfessorDto professorDto = criarProfessorDtoComId();

        when(repository.save(any(ProfessorEntity.class))).thenThrow(RuntimeException.class);
        when(repository.findById(anyLong())).thenReturn(Optional.of(criarProfessorEntity()));

        assertThatExceptionOfType(RequestParamException.class)
                .isThrownBy(() -> service.atualizar(professorDto))
                .withMessage("Erro ao atualizar recurso, verifique os parametros digitados");

        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Deve Lancar ResourceNotFoundExeption Quando Não Encontrar Um Professor Para Atualizar")
    void deveLancarResourceNotFoundExeptionQuandoNaoAtualizarUmProfessor() {
        ProfessorDto professorDto = criarProfessorDtoComId();

        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatExceptionOfType(ResourceNotFoundExeption.class)
                .isThrownBy(() -> service.atualizar(professorDto))
                .withMessage("Recurso não encontrado com o ID: 1");

        verify(repository, never()).save(any(ProfessorEntity.class));
        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Deve deletar Um professor")
    void deveDeletarUmProfessor() throws Exception {
        doNothing().when(repository).delete(any(ProfessorEntity.class));
        doNothing().when(usuarioService).excluir(anyString());
        when(repository.findById(anyLong())).thenReturn(Optional.of(criarProfessorEntity()));

        service.deletar(1L);

        verify(repository, times(1)).delete(any(ProfessorEntity.class));
        verify(repository, times(2)).findById(anyLong());
    }

    @Test
    @DisplayName("Deve Lancar ResourceNotFoundExeption Quando Não Encontrar Um Professor Para Deletar")
    void deveLancarResourceNotFoundExeptionQuandoNaoEncontrarUmProfessorParaDeletar() throws Exception {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        doNothing().when(usuarioService).excluir(anyString());

        assertThatExceptionOfType(ResourceNotFoundExeption.class)
                .isThrownBy(() -> service.deletar(1L))
                .withMessage("Recurso não encontrado com o ID: 1");

        verify(repository, never()).delete(any(ProfessorEntity.class));
        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Deve Lancar RequestParamException Quando Não Deletar Um Professor")
    void deveLancarRequestParamExceptionQuandoNaoDeletarUmProfessor() throws Exception {
        doThrow(RuntimeException.class).when(repository).delete(any(ProfessorEntity.class));
        doNothing().when(usuarioService).excluir(anyString());

        when(repository.findById(anyLong())).thenReturn(Optional.of(criarProfessorEntity()));

        assertThatExceptionOfType(RequestParamException.class)
                .isThrownBy(() -> service.deletar(1L))
                .withMessage("Não foi possível deletar o recurso com o id: 1");

        verify(repository, times(2)).findById(anyLong());
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

    @Test
    @DisplayName("Deve Lancar RequestParamException Quando Não Listar Professores")
    void deveLancarRequestParamExceptionQuandoNaoListarProfessores() {
        when(repository.findAll()).thenThrow(RuntimeException.class);

        assertThatExceptionOfType(RequestParamException.class)
                .isThrownBy(() -> service.listar())
                .withMessage("Erro ao listar recurso");
    }
}