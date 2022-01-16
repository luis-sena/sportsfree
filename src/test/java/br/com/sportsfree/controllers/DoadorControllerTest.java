package br.com.sportsfree.controllers;

import br.com.sportsfree.dto.DoadorDto;
import br.com.sportsfree.service.impl.DoadorService;
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

import java.util.List;

import static br.com.sportsfree.utils.DoadorTesteUtils.criarDoadorDto;
import static br.com.sportsfree.utils.DoadorTesteUtils.criarDoadorDtoComId;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DoadorController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DoadorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DoadorService service;

    @Test
    @DisplayName("Deve salvar Um doador")
    void deveSalvarUmDoador() throws Exception {
        DoadorDto retorno = criarDoadorDtoComId();
        DoadorDto doadorDto = criarDoadorDto();

        when(service.salvar(any(DoadorDto.class))).thenReturn(retorno);

        mockMvc.perform(post("/doador")
                        .content(objectMapper.writeValueAsString(doadorDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id").value(retorno.getId()))
                .andExpect(jsonPath("$.nome").value(retorno.getNome()))
                .andExpect(jsonPath("$.cpfCnpj").value(retorno.getCpfCnpj()))
                .andExpect(jsonPath("$.rg").value(retorno.getRg()))
                .andExpect(jsonPath("$.contato").value(retorno.getContato()))
                .andExpect(jsonPath("$.endereco").value(retorno.getEndereco()));
    }

    @Test
    @WithMockUser(authorities = "SCOPE_ADMIN")
    @DisplayName("Deve atualizar Um doador")
    void deveAtualizarUmDoador() throws Exception {
        DoadorDto doadorDto = criarDoadorDtoComId();

        when(service.atualizar(any(DoadorDto.class))).thenReturn(doadorDto);

        mockMvc.perform(put("/doador")
                        .content(objectMapper.writeValueAsString(doadorDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(doadorDto.getId()))
                .andExpect(jsonPath("$.nome").value(doadorDto.getNome()))
                .andExpect(jsonPath("$.cpfCnpj").value(doadorDto.getCpfCnpj()))
                .andExpect(jsonPath("$.rg").value(doadorDto.getRg()))
                .andExpect(jsonPath("$.contato").value(doadorDto.getContato()))
                .andExpect(jsonPath("$.endereco").value(doadorDto.getEndereco()));
    }

    @Test
    @WithMockUser(authorities = "SCOPE_ADMIN")
    @DisplayName("Deve deletar Um doador")
    void deveDeletarUmDoador() throws Exception {
        doNothing().when(service).deletar(anyLong());
        mockMvc.perform(delete("/doador/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(authorities = "SCOPE_DOADOR")
    @DisplayName("Deve recuperar Um doador")
    void deveRecuperarUmDoador() throws Exception {
        DoadorDto doadorDto = criarDoadorDtoComId();

        when(service.recuperar(anyLong())).thenReturn(doadorDto);

        mockMvc.perform(get("/doador/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(doadorDto.getId()))
                .andExpect(jsonPath("$.nome").value(doadorDto.getNome()))
                .andExpect(jsonPath("$.cpfCnpj").value(doadorDto.getCpfCnpj()))
                .andExpect(jsonPath("$.rg").value(doadorDto.getRg()))
                .andExpect(jsonPath("$.contato").value(doadorDto.getContato()))
                .andExpect(jsonPath("$.endereco").value(doadorDto.getEndereco()));
    }

    @Test
    @WithMockUser(authorities = "SCOPE_ADMIN")
    @DisplayName("Deve recuperar uma lista de doadores")
    void deveRecuperarUmaListaDeDoadores() throws Exception {
        DoadorDto doadorDto = criarDoadorDtoComId();

        when(service.listar()).thenReturn(List.of(doadorDto));

        mockMvc.perform(get("/doador"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(doadorDto.getId()))
                .andExpect(jsonPath("$[0].nome").value(doadorDto.getNome()))
                .andExpect(jsonPath("$[0].cpfCnpj").value(doadorDto.getCpfCnpj()))
                .andExpect(jsonPath("$[0].rg").value(doadorDto.getRg()))
                .andExpect(jsonPath("$[0].contato").value(doadorDto.getContato()))
                .andExpect(jsonPath("$[0].endereco").value(doadorDto.getEndereco()));
    }
}