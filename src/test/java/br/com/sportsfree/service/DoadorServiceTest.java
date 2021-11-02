package br.com.sportsfree.service;

import br.com.sportsfree.dto.DoadorDto;
import br.com.sportsfree.entity.DoadorEntity;
import br.com.sportsfree.error.RequestParamException;
import br.com.sportsfree.error.ResourceNotFoundExeption;
import br.com.sportsfree.mapper.DoadorMapper;
import br.com.sportsfree.mapper.DoadorMapper;
import br.com.sportsfree.repository.DoadorRepository;
import br.com.sportsfree.repository.DoadorRepository;
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

import static br.com.sportsfree.utils.DoadorTesteUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class DoadorServiceTest {

    @Mock
    private DoadorRepository repository;
    @Spy
    private DoadorMapper mapper;

    @InjectMocks
    private DoadorService service;

    @Test
    @DisplayName("Deve salvar Um doador")
    void deveSalvarUmDoador() {
        DoadorDto doadorDto = criarDoadorDto();

        when(repository.save(any(DoadorEntity.class))).thenReturn(criarDoadorEntity());

        DoadorDto doadorSalvo = service.salvar(doadorDto);

        verify(repository, times(1)).save(any(DoadorEntity.class));
        assertThat(doadorDto).isEqualTo(doadorSalvo);
    }

    @Test
    @DisplayName("Deve Lancar RequestParamException Quando Não Salvar Um Doador")
    void deveLancarRequestParamExceptionQuandoNaoSalvarUmDoador() {
        DoadorDto doadorDto = criarDoadorDto();

        when(repository.save(any(DoadorEntity.class))).thenThrow(RuntimeException.class);

        assertThatExceptionOfType(RequestParamException.class)
                .isThrownBy(() -> service.salvar(doadorDto))
                .withMessage("Erro ao salvar recurso, verifique os parametros digitados");
    }

    @Test
    @DisplayName("Deve atualizar Um doador")
    void deveAtualizarUmDoador() {
        DoadorDto doadorDto = criarDoadorDtoComId();

        when(repository.save(any(DoadorEntity.class))).thenReturn(criarDoadorEntity());
        when(repository.findById(anyLong())).thenReturn(Optional.of(criarDoadorEntity()));

        DoadorDto doadorSalvo = service.atualizar(doadorDto);

        verify(repository, times(1)).save(any(DoadorEntity.class));
        verify(repository, times(1)).findById(anyLong());
        assertThat(doadorDto).isEqualTo(doadorSalvo);
    }

    @Test
    @DisplayName("Deve Lancar RequestParamException Quando Não Atualizar Um Doador")
    void deveLancarRequestParamExceptionQuandoNaoAtualizarUmDoador() {
        DoadorDto doadorDto = criarDoadorDtoComId();

        when(repository.save(any(DoadorEntity.class))).thenThrow(RuntimeException.class);
        when(repository.findById(anyLong())).thenReturn(Optional.of(criarDoadorEntity()));

        assertThatExceptionOfType(RequestParamException.class)
                .isThrownBy(() -> service.atualizar(doadorDto))
                .withMessage("Erro ao atualizar recurso, verifique os parametros digitados");

        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Deve Lancar ResourceNotFoundExeption Quando Não Encontrar Um Doador Para Atualizar")
    void deveLancarResourceNotFoundExeptionQuandoNaoAtualizarUmDoador() {
        DoadorDto doadorDto = criarDoadorDtoComId();

        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatExceptionOfType(ResourceNotFoundExeption.class)
                .isThrownBy(() -> service.atualizar(doadorDto))
                .withMessage("Recurso não encontrado com o ID: 1");

        verify(repository, never()).save(any(DoadorEntity.class));
        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Deve deletar Um doador")
    void deveDeletarUmDoador() {
        doNothing().when(repository).delete(any(DoadorEntity.class));
        when(repository.findById(anyLong())).thenReturn(Optional.of(criarDoadorEntity()));

        service.deletar(1L);

        verify(repository, times(1)).delete(any(DoadorEntity.class));
        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Deve Lancar ResourceNotFoundExeption Quando Não Encontrar Um Doador Para Deletar")
    void deveLancarResourceNotFoundExeptionQuandoNaoEncontrarUmDoadorParaDeletar() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatExceptionOfType(ResourceNotFoundExeption.class)
                .isThrownBy(() -> service.deletar(1L))
                .withMessage("Recurso não encontrado com o ID: 1");

        verify(repository, never()).delete(any(DoadorEntity.class));
        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Deve Lancar RequestParamException Quando Não Deletar Um Doador")
    void deveLancarRequestParamExceptionQuandoNaoDeletarUmDoador() {
        doThrow(RuntimeException.class).when(repository).delete(any(DoadorEntity.class));
        when(repository.findById(anyLong())).thenReturn(Optional.of(criarDoadorEntity()));

        assertThatExceptionOfType(RequestParamException.class)
                .isThrownBy(() -> service.deletar(1L))
                .withMessage("Não foi possível deletar o recurso com o id: 1");

        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Deve recuperar Um doador")
    void deveRecuperarUmDoador() {
        DoadorEntity doadorEntity = criarDoadorEntity();

        when(repository.findById(anyLong())).thenReturn(Optional.of(criarDoadorEntity()));

        DoadorDto doadorDto = service.recuperar(1L);

        verify(repository, times(1)).findById(anyLong());
        assertThat(mapper.mapToDto(doadorEntity)).isEqualTo(doadorDto);
    }

    @Test
    @DisplayName("Deve recuperar uma lista de doadores")
    void deveRecuperarUmaListaDeDoadores() {
        List<DoadorEntity> doadores = List.of(criarDoadorEntityComId());

        when(repository.findAll()).thenReturn(doadores);

        List<DoadorDto> lista = service.listar();

        verify(repository, times(1)).findAll();
        assertThat(lista).hasSize(1);
    }

    @Test
    @DisplayName("Deve Lancar RequestParamException Quando Não Listar Doadores")
    void deveLancarRequestParamExceptionQuandoNaoListarDoadores() {
        when(repository.findAll()).thenThrow(RuntimeException.class);

        assertThatExceptionOfType(RequestParamException.class)
                .isThrownBy(() -> service.listar())
                .withMessage("Erro ao listar recurso");
    }
}