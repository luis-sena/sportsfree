package br.com.sportsfree.controllers;


import br.com.sportsfree.dto.ProfessorDto;
import br.com.sportsfree.service.impl.ProfessorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static br.com.sportsfree.utils.ProfessorTesteUtils.criarProfessorDto;
import static br.com.sportsfree.utils.ProfessorTesteUtils.criarProfessorDtoComId;
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

    @BeforeAll
    void setUp() {
        FirebaseApp.initializeApp();
    }

    @Test
    @WithMockUser(authorities = "SCOPE_ADMIN")
    @DisplayName("Deve salvar Um professor")
    void deveSalvarUmProfessor() throws Exception {
        ProfessorDto retorno = criarProfessorDtoComId();
        ProfessorDto professorDto = criarProfessorDto();

        when(service.salvar(any(ProfessorDto.class))).thenReturn(retorno);

        mockMvc.perform(post("/professor")
                        .content(objectMapper.writeValueAsString(professorDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id").value(retorno.getId()))
                .andExpect(jsonPath("$.nome").value(retorno.getNome()))
                .andExpect(jsonPath("$.cpf").value(retorno.getCpf()))
                .andExpect(jsonPath("$.rg").value(retorno.getRg()))
                .andExpect(jsonPath("$.email").value(retorno.getEmail()))
                .andExpect(jsonPath("$.endereco").value(retorno.getEndereco()));
    }

    @Test
    @WithMockUser(authorities = "SCOPE_ADMIN")
    @DisplayName("Deve atualizar Um professor")
    void deveAtualizarUmProfessor() throws Exception {
        ProfessorDto professorDto = criarProfessorDtoComId();

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
    @WithMockUser(authorities = "SCOPE_ADMIN")
    @DisplayName("Deve deletar Um professor")
    void deveDeletarUmProfessor() throws Exception {
        doNothing().when(service).deletar(anyLong());
        mockMvc.perform(delete("/professor/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(authorities = "SCOPE_PROFESSOR")
    @DisplayName("Deve recuperar Um professor")
    void deveRecuperarUmProfessor() throws Exception {
        ProfessorDto professorDto = criarProfessorDtoComId();

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
    @WithMockUser(authorities = "SCOPE_PROFESSOR")
    @DisplayName("Deve recuperar uma lista de professores")
    void deveRecuperarUmaListaDeProfessores() throws Exception {
        ProfessorDto professorDto = criarProfessorDtoComId();

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