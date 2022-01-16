package br.com.sportsfree.controllers;

import static br.com.sportsfree.utils.EsporteTesteUtils.criarEsporteDto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import br.com.sportsfree.dto.EsporteDto;
import br.com.sportsfree.service.impl.EsporteService;

@WebMvcTest(EsporteController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EsporteControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EsporteService service;

    @Test
    @WithMockUser(authorities = "SCOPE_PROFESSOR")
    @DisplayName("Deve salvar Um esporte")
    void deveSalvarUmEsporte() throws Exception {
        EsporteDto esporteDto = criarEsporteDto();

        when(service.salvar(any(EsporteDto.class))).thenReturn(esporteDto);

        mockMvc.perform(post("/esporte")
                        .content(objectMapper.writeValueAsString(esporteDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id").value(esporteDto.getId()))
                .andExpect(jsonPath("$.nome").value(esporteDto.getNome()))
                .andExpect(jsonPath("$.descricao").value(esporteDto.getDescricao()))
                .andExpect(jsonPath("$.urlImagem").value(esporteDto.getUrlImagem()));
    }

    @Test
    @WithMockUser(authorities = "SCOPE_PROFESSOR")
    @DisplayName("Deve atualizar Um esporte")
    void deveAtualizarUmEsporte() throws Exception {
        EsporteDto esporteDto = criarEsporteDto();

        when(service.atualizar(any(EsporteDto.class))).thenReturn(esporteDto);

        mockMvc.perform(put("/esporte")
                        .content(objectMapper.writeValueAsString(esporteDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(esporteDto.getId()))
                .andExpect(jsonPath("$.nome").value(esporteDto.getNome()))
                .andExpect(jsonPath("$.descricao").value(esporteDto.getDescricao()))
                .andExpect(jsonPath("$.urlImagem").value(esporteDto.getUrlImagem()));
    }

    @Test
    @WithMockUser(authorities = "SCOPE_PROFESSOR")
    @DisplayName("Deve deletar Um esporte")
    void deveDeletarUmEsporte() throws Exception {
        doNothing().when(service).deletar(anyLong());
        mockMvc.perform(delete("/esporte/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(authorities = "SCOPE_PROFESSOR")
    @DisplayName("Deve recuperar Um esporte")
    void deveRecuperarUmEsporte() throws Exception {
        EsporteDto esporteDto = criarEsporteDto();

        when(service.recuperar(anyLong())).thenReturn(esporteDto);

        mockMvc.perform(get("/esporte/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(esporteDto.getId()))
                .andExpect(jsonPath("$.nome").value(esporteDto.getNome()))
                .andExpect(jsonPath("$.descricao").value(esporteDto.getDescricao()))
                .andExpect(jsonPath("$.urlImagem").value(esporteDto.getUrlImagem()));
    }

    @Test
    @WithMockUser(authorities = "SCOPE_PROFESSOR")
    @DisplayName("Deve recuperar uma lista de esportes")
    void deveRecuperarUmaListaDeProfessores() throws Exception {
        EsporteDto esporteDto = criarEsporteDto();

        when(service.listar()).thenReturn(List.of(esporteDto));

        mockMvc.perform(get("/esporte"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(esporteDto.getId()))
                .andExpect(jsonPath("$[0].nome").value(esporteDto.getNome()))
                .andExpect(jsonPath("$[0].descricao").value(esporteDto.getDescricao()))
                .andExpect(jsonPath("$[0].urlImagem").value(esporteDto.getUrlImagem()));
    }

}
