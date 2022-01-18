
package br.com.sportsfree.service;

import br.com.sportsfree.dto.EsporteDto;
import br.com.sportsfree.entity.EsporteEntity;
import br.com.sportsfree.error.RequestParamException;
import br.com.sportsfree.error.ResourceNotFoundExeption;
import br.com.sportsfree.mapper.EsporteMapper;
import br.com.sportsfree.repository.EsporteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static br.com.sportsfree.utils.EsporteTesteUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class EsporteServiceTest {
    
    @Mock
    private EsporteRepository repository;
    @Spy
    private EsporteMapper mapper;

    @InjectMocks
    private EsporteService service;

    @Test
    @DisplayName("Deve salvar Um esporte")
    void deveSalvarUmEsporte() {
        EsporteDto esporteDto = criarEsporteDto();

        when(repository.saveAndFlush(any(EsporteEntity.class))).thenReturn(criarEsporteEntity());

        EsporteDto esporteSalvo = service.salvar(esporteDto);

        verify(repository, times(1)).saveAndFlush(any(EsporteEntity.class));
        assertThat(esporteDto).isEqualTo(esporteSalvo);
    }

    @Test
    @DisplayName("Deve Lancar RequestParamException Quando Não Salvar Um Esporte")
    void deveLancarRequestParamExceptionQuandoNaoSalvarUmEsporte() {
        EsporteDto esporteDto = criarEsporteDto();

        when(repository.saveAndFlush(any(EsporteEntity.class))).thenThrow(RuntimeException.class);

        assertThatExceptionOfType(RequestParamException.class)
                .isThrownBy(() -> service.salvar(esporteDto))
                .withMessage("Erro ao salvar recurso, verifique os parametros digitados");
    }

    @Test
    @DisplayName("Deve atualizar Um Esporte")
    void deveAtualizarUmEsporte() {
        EsporteDto esporteDto = criarEsporteDtoComId();

        when(repository.saveAndFlush(any(EsporteEntity.class))).thenReturn(criarEsporteEntity());
        when(repository.findById(anyLong())).thenReturn(Optional.of(criarEsporteEntity()));

        EsporteDto esporteSalvo = service.atualizar(esporteDto);

        verify(repository, times(1)).saveAndFlush(any(EsporteEntity.class));
        verify(repository, times(1)).findById(anyLong());
        assertThat(esporteDto).isEqualTo(esporteSalvo);
    }

    @Test
    @DisplayName("Deve Lancar RequestParamException Quando Não Atualizar Um Esporte")
    void deveLancarRequestParamExceptionQuandoNaoAtualizarUmEsporte() {
        EsporteDto esporteDto = criarEsporteDtoComId();

        when(repository.saveAndFlush(any(EsporteEntity.class))).thenThrow(RuntimeException.class);
        when(repository.findById(anyLong())).thenReturn(Optional.of(criarEsporteEntity()));

        assertThatExceptionOfType(RequestParamException.class)
                .isThrownBy(() -> service.atualizar(esporteDto))
                .withMessage("Erro ao atualizar recurso, verifique os parametros digitados");

        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Deve Lancar ResourceNotFoundExeption Quando Não Encontrar Um Esporte Para Atualizar")
    void deveLancarResourceNotFoundExeptionQuandoNaoAtualizarUmEsporte() {
        EsporteDto esporteDto = criarEsporteDtoComId();

        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatExceptionOfType(ResourceNotFoundExeption.class)
                .isThrownBy(() -> service.atualizar(esporteDto))
                .withMessage("Recurso não encontrado com o ID: 1");

        verify(repository, never()).save(any(EsporteEntity.class));
        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Deve deletar Um Esporte")
    void deveDeletarUmEsporte() {
        doNothing().when(repository).delete(any(EsporteEntity.class));
        when(repository.findById(anyLong())).thenReturn(Optional.of(criarEsporteEntity()));

        service.deletar(1L);

        verify(repository, times(1)).delete(any(EsporteEntity.class));
        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Deve Lancar ResourceNotFoundExeption Quando Não Encontrar Um Esporte Para Deletar")
    void deveLancarResourceNotFoundExeptionQuandoNaoEncontrarUmEsporteParaDeletar() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatExceptionOfType(ResourceNotFoundExeption.class)
                .isThrownBy(() -> service.deletar(1L))
                .withMessage("Recurso não encontrado com o ID: 1");

        verify(repository, never()).delete(any(EsporteEntity.class));
        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Deve Lancar RequestParamException Quando Não Deletar Um Esporte")
    void deveLancarRequestParamExceptionQuandoNaoDeletarUmEsporte() {
        doThrow(RuntimeException.class).when(repository).delete(any(EsporteEntity.class));
        when(repository.findById(anyLong())).thenReturn(Optional.of(criarEsporteEntity()));

        assertThatExceptionOfType(RequestParamException.class)
                .isThrownBy(() -> service.deletar(1L))
                .withMessage("Não foi possível deletar o recurso com o id: 1");

        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Deve recuperar Um Esporte")
    void deveRecuperarUmEsporte() {
        EsporteEntity esporteEntity = criarEsporteEntity();

        when(repository.findById(anyLong())).thenReturn(Optional.of(criarEsporteEntity()));

        EsporteDto esporteDto = service.recuperar(1L);

        verify(repository, times(1)).findById(anyLong());
        assertThat(mapper.mapToDto(esporteEntity)).isEqualTo(esporteDto);
    }

    @Test
    @DisplayName("Deve recuperar uma lista de Esportes")
    void deveRecuperarUmaListaDeEsportes() {
        List<EsporteEntity> esportes = List.of(criarEsporteEntityComId());

        when(repository.findAll()).thenReturn(esportes);

        List<EsporteDto> lista = service.listar();

        verify(repository, times(1)).findAll();
        assertThat(lista).hasSize(1);
    }

    @Test
    @DisplayName("Deve Lancar RequestParamException Quando Não Listar Esportes")
    void deveLancarRequestParamExceptionQuandoNaoListarEsportes() {
        when(repository.findAll()).thenThrow(RuntimeException.class);

        assertThatExceptionOfType(RequestParamException.class)
                .isThrownBy(() -> service.listar())
                .withMessage("Erro ao listar recurso");
    }

}
