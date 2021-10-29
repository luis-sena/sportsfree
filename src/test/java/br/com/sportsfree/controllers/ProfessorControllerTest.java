package br.com.sportsfree.controllers;


import br.com.sportsfree.dto.ProfessorDto;
import br.com.sportsfree.service.ProfessorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static br.com.sportsfree.utils.ProfessorTesteUtils.criarProfessorDto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProfessorController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProfessorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProfessorService service;

    @Test
    @DisplayName("Deve salvar Um professor")
    void deveSalvarUmProfessor() throws Exception {
        ProfessorDto professorDto = criarProfessorDto();

        when(service.salvar(any(ProfessorDto.class))).thenReturn(professorDto);

        mockMvc.perform(post("/professor")
                        .content(objectMapper.writeValueAsString(professorDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id").value(professorDto.getId()))
                .andExpect(jsonPath("$.nome").value(professorDto.getNome()))
                .andExpect(jsonPath("$.cpf").value(professorDto.getCpf()))
                .andExpect(jsonPath("$.rg").value(professorDto.getRg()))
                .andExpect(jsonPath("$.email").value(professorDto.getEmail()))
                .andExpect(jsonPath("$.endereco").value(professorDto.getEndereco()));
    }

    @Test
    @DisplayName("Deve atualizar Um professor")
    void deveAtualizarUmProfessor() throws Exception {
        ProfessorDto professorDto = criarProfessorDto();

        when(service.atualizar(any(ProfessorDto.class))).thenReturn(professorDto);

        mockMvc.perform(put("/professor")
                        .content(objectMapper.writeValueAsString(professorDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(professorDto.getId()))
                .andExpect(jsonPath("$.nome").value(professorDto.getNome()))
                .andExpect(jsonPath("$.cpf").value(professorDto.getCpf()))
                .andExpect(jsonPath("$.rg").value(professorDto.getRg()))
                .andExpect(jsonPath("$.email").value(professorDto.getEmail()))
                .andExpect(jsonPath("$.endereco").value(professorDto.getEndereco()));
    }

    @Test
    @DisplayName("Deve deletar Um professor")
    void deveDeletarUmProfessor() throws Exception {
        doNothing().when(service).deletar(anyLong());
        mockMvc.perform(delete("/professor/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve recuperar Um professor")
    void deveRecuperarUmProfessor() throws Exception {
        ProfessorDto professorDto = criarProfessorDto();

        when(service.recuperar(anyLong())).thenReturn(professorDto);

        mockMvc.perform(get("/professor/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(professorDto.getId()))
                .andExpect(jsonPath("$.nome").value(professorDto.getNome()))
                .andExpect(jsonPath("$.cpf").value(professorDto.getCpf()))
                .andExpect(jsonPath("$.rg").value(professorDto.getRg()))
                .andExpect(jsonPath("$.email").value(professorDto.getEmail()))
                .andExpect(jsonPath("$.endereco").value(professorDto.getEndereco()));
    }

    @Test
    @DisplayName("Deve recuperar uma lista de professores")
    void deveRecuperarUmaListaDeProfessores() throws Exception {
        ProfessorDto professorDto = criarProfessorDto();

        when(service.listar()).thenReturn(List.of(professorDto));

        mockMvc.perform(get("/professor"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(professorDto.getId()))
                .andExpect(jsonPath("$[0].nome").value(professorDto.getNome()))
                .andExpect(jsonPath("$[0].cpf").value(professorDto.getCpf()))
                .andExpect(jsonPath("$[0].rg").value(professorDto.getRg()))
                .andExpect(jsonPath("$[0].email").value(professorDto.getEmail()))
                .andExpect(jsonPath("$[0].endereco").value(professorDto.getEndereco()));
    }
}