package br.com.sportsfree.service;

import static br.com.sportsfree.utils.CursoTesteUtils.criarCursoDto;
import static br.com.sportsfree.utils.CursoTesteUtils.criarCursoDtoComId;
import static br.com.sportsfree.utils.CursoTesteUtils.criarCursoEntity;
import static br.com.sportsfree.utils.CursoTesteUtils.criarCursoEntityComId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.sportsfree.dto.CursoDto;
import br.com.sportsfree.entity.CursoEntity;
import br.com.sportsfree.error.RequestParamException;
import br.com.sportsfree.error.ResourceNotFoundExeption;
import br.com.sportsfree.mapper.CursoMapper;
import br.com.sportsfree.repository.CursoRepository;

@ExtendWith(SpringExtension.class)
public class CursoServiceTest {
    
    @Mock
    private CursoRepository repository;
    @Spy
    private CursoMapper mapper;

    @InjectMocks
    private CursoService service;

    @Test
    @DisplayName("Deve salvar Um curso")
    void deveSalvarUmCurso() {
        CursoDto cursoDto = criarCursoDto();

        when(repository.save(any(CursoEntity.class))).thenReturn(criarCursoEntity());

        CursoDto cursoSalvo = service.salvar(cursoDto);

        verify(repository, times(1)).save(any(CursoEntity.class));
        assertThat(cursoDto).isEqualTo(cursoSalvo);
    }
    
    @Test
    @DisplayName("Deve Lancar RequestParamException Quando Não Salvar Um Curso")
    void deveLancarRequestParamExceptionQuandoNaoSalvarUmCurso() {
        CursoDto cursoDto = criarCursoDto();

        when(repository.save(any(CursoEntity.class))).thenThrow(RuntimeException.class);

        assertThatExceptionOfType(RequestParamException.class)
                .isThrownBy(() -> service.salvar(cursoDto))
                .withMessage("Erro ao salvar recurso, verifique os parametros digitados");
    }

    @Test
    @DisplayName("Deve atualizar Um Curso")
    void deveAtualizarUmCurso() {
        CursoDto cursoDto = criarCursoDtoComId();

        when(repository.save(any(CursoEntity.class))).thenReturn(criarCursoEntity());
        when(repository.findById(anyLong())).thenReturn(Optional.of(criarCursoEntity()));

        CursoDto cursoSalvo = service.atualizar(cursoDto);

        verify(repository, times(1)).save(any(CursoEntity.class));
        verify(repository, times(1)).findById(anyLong());
        assertThat(cursoDto).isEqualTo(cursoSalvo);
    }

    @Test
    @DisplayName("Deve Lancar RequestParamException Quando Não Atualizar Um Curso")
    void deveLancarRequestParamExceptionQuandoNaoAtualizarUmCurso() {
        CursoDto cursoDto = criarCursoDtoComId();

        when(repository.save(any(CursoEntity.class))).thenThrow(RuntimeException.class);
        when(repository.findById(anyLong())).thenReturn(Optional.of(criarCursoEntity()));

        assertThatExceptionOfType(RequestParamException.class)
                .isThrownBy(() -> service.atualizar(cursoDto))
                .withMessage("Erro ao atualizar recurso, verifique os parametros digitados");

        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Deve Lancar ResourceNotFoundExeption Quando Não Encontrar Um Curso Para Atualizar")
    void deveLancarResourceNotFoundExeptionQuandoNaoAtualizarUmCurso() {
        CursoDto cursoDto = criarCursoDtoComId();

        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatExceptionOfType(ResourceNotFoundExeption.class)
                .isThrownBy(() -> service.atualizar(cursoDto))
                .withMessage("Recurso não encontrado com o ID: 1");

        verify(repository, never()).save(any(CursoEntity.class));
        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Deve deletar Um Curso")
    void deveDeletarUmCurso() {
        doNothing().when(repository).delete(any(CursoEntity.class));
        when(repository.findById(anyLong())).thenReturn(Optional.of(criarCursoEntity()));

        service.deletar(1L);

        verify(repository, times(1)).delete(any(CursoEntity.class));
        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Deve Lancar ResourceNotFoundExeption Quando Não Encontrar Um Curso Para Deletar")
    void deveLancarResourceNotFoundExeptionQuandoNaoEncontrarUmCursoParaDeletar() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatExceptionOfType(ResourceNotFoundExeption.class)
                .isThrownBy(() -> service.deletar(1L))
                .withMessage("Recurso não encontrado com o ID: 1");

        verify(repository, never()).delete(any(CursoEntity.class));
        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Deve Lancar RequestParamException Quando Não Deletar Um Curso")
    void deveLancarRequestParamExceptionQuandoNaoDeletarUmCurso() {
        doThrow(RuntimeException.class).when(repository).delete(any(CursoEntity.class));
        when(repository.findById(anyLong())).thenReturn(Optional.of(criarCursoEntity()));

        assertThatExceptionOfType(RequestParamException.class)
                .isThrownBy(() -> service.deletar(1L))
                .withMessage("Não foi possível deletar o recurso com o id: 1");

        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Deve recuperar Um Curso")
    void deveRecuperarUmCurso() {
        CursoEntity cursoEntity = criarCursoEntity();

        when(repository.findById(anyLong())).thenReturn(Optional.of(criarCursoEntity()));

        CursoDto cursoDto = service.recuperar(1L);

        verify(repository, times(1)).findById(anyLong());
        assertThat(mapper.mapToDto(cursoEntity)).isEqualTo(cursoDto);
    }

    @Test
    @DisplayName("Deve recuperar uma lista de Cursos")
    void deveRecuperarUmaListaDeCursos() {
        List<CursoEntity> cursos = List.of(criarCursoEntityComId());

        when(repository.findAll()).thenReturn(cursos);

        List<CursoDto> lista = service.listar();

        verify(repository, times(1)).findAll();
        assertThat(lista).hasSize(1);
    }

    @Test
    @DisplayName("Deve Lancar RequestParamException Quando Não Listar Cursos")
    void deveLancarRequestParamExceptionQuandoNaoListarCursos() {
        when(repository.findAll()).thenThrow(RuntimeException.class);

        assertThatExceptionOfType(RequestParamException.class)
                .isThrownBy(() -> service.listar())
                .withMessage("Erro ao listar recurso");
    }    

}
