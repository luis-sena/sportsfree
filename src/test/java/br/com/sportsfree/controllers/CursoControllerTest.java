package br.com.sportsfree.controllers;

import static br.com.sportsfree.utils.CursoTesteUtils.criarCursoDto;
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
import org.springframework.test.web.servlet.MockMvc;

import br.com.sportsfree.dto.CursoDto;
import br.com.sportsfree.service.CursoService;

@WebMvcTest(CursoController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CursoControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CursoService service;

    @Test
    @DisplayName("Deve salvar Um curso")
    void deveSalvarUmCurso() throws Exception {
        CursoDto cursoDto = criarCursoDto();

        when(service.salvar(any(CursoDto.class))).thenReturn(cursoDto);

        mockMvc.perform(post("/curso")
                        .content(objectMapper.writeValueAsString(cursoDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id").value(cursoDto.getId()))                
                .andExpect(jsonPath("$.descricao").value(cursoDto.getDescricao()))
                .andExpect(jsonPath("$.professor").value(cursoDto.getProfessor()))
                .andExpect(jsonPath("$.esporte").value(cursoDto.getEsporte()))
                .andExpect(jsonPath("$.local").value(cursoDto.getLocal()))
                .andExpect(jsonPath("$.dia").value(cursoDto.getDia()))
                .andExpect(jsonPath("$.horario_inicio").value(cursoDto.getHorario_inicio()))
                .andExpect(jsonPath("$.horario_fim").value(cursoDto.getHorario_fim()))
                .andExpect(jsonPath("$.quantidade_max_alunos").value(cursoDto.getQuantidade_max_alunos()));
    }

    @Test
    @DisplayName("Deve atualizar Um curso")
    void deveAtualizarUmcurso() throws Exception {
        CursoDto cursoDto = criarCursoDto();

        when(service.atualizar(any(CursoDto.class))).thenReturn(cursoDto);

        mockMvc.perform(put("/curso")
                        .content(objectMapper.writeValueAsString(cursoDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(cursoDto.getId()))                
                .andExpect(jsonPath("$.descricao").value(cursoDto.getDescricao()))
                .andExpect(jsonPath("$.professor").value(cursoDto.getProfessor()))
                .andExpect(jsonPath("$.esporte").value(cursoDto.getEsporte()))
                .andExpect(jsonPath("$.local").value(cursoDto.getLocal()))
                .andExpect(jsonPath("$.dia").value(cursoDto.getDia()))
                .andExpect(jsonPath("$.horario_inicio").value(cursoDto.getHorario_inicio()))
                .andExpect(jsonPath("$.horario_fim").value(cursoDto.getHorario_fim()))
                .andExpect(jsonPath("$.quantidade_max_alunos").value(cursoDto.getQuantidade_max_alunos()));
    }

    @Test
    @DisplayName("Deve deletar Um curso")
    void deveDeletarUmcurso() throws Exception {
        doNothing().when(service).deletar(anyLong());
        mockMvc.perform(delete("/curso/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve recuperar Um curso")
    void deveRecuperarUmcurso() throws Exception {
        CursoDto cursoDto = criarCursoDto();

        when(service.recuperar(anyLong())).thenReturn(cursoDto);

        mockMvc.perform(get("/curso/1"))
                .andExpect(status().isOk())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(cursoDto.getId()))                
                .andExpect(jsonPath("$.descricao").value(cursoDto.getDescricao()))
                .andExpect(jsonPath("$.professor").value(cursoDto.getProfessor()))
                .andExpect(jsonPath("$.esporte").value(cursoDto.getEsporte()))
                .andExpect(jsonPath("$.local").value(cursoDto.getLocal()))
                .andExpect(jsonPath("$.dia").value(cursoDto.getDia()))
                .andExpect(jsonPath("$.horario_inicio").value(cursoDto.getHorario_inicio()))
                .andExpect(jsonPath("$.horario_fim").value(cursoDto.getHorario_fim()))
                .andExpect(jsonPath("$.quantidade_max_alunos").value(cursoDto.getQuantidade_max_alunos()));
    }

    @Test
    @DisplayName("Deve recuperar uma lista de cursos")
    void deveRecuperarUmaListaDeCursos() throws Exception {
        CursoDto cursoDto = criarCursoDto();

        when(service.listar()).thenReturn(List.of(cursoDto));

        mockMvc.perform(get("/curso"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(cursoDto.getId()))                
                .andExpect(jsonPath("$[0].descricao").value(cursoDto.getDescricao()))
                .andExpect(jsonPath("$[0].professor").value(cursoDto.getProfessor()))
                .andExpect(jsonPath("$[0].esporte").value(cursoDto.getEsporte()))
                .andExpect(jsonPath("$[0].local").value(cursoDto.getLocal()))
                .andExpect(jsonPath("$[0].dia").value(cursoDto.getDia()))
                .andExpect(jsonPath("$[0].horario_inicio").value(cursoDto.getHorario_inicio()))
                .andExpect(jsonPath("$[0].horario_fim").value(cursoDto.getHorario_fim()))
                .andExpect(jsonPath("$[0].quantidade_max_alunos").value(cursoDto.getQuantidade_max_alunos()));
    }

}
