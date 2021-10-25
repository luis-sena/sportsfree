package br.com.sportsfree.repository;

import br.com.sportsfree.entity.ProfessorEntity;
import br.com.sportsfree.entity.embeddable.Endereco;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProfessorRepositoryTest {

    @Autowired
    private ProfessorRepository repository;

    @Test
    @DisplayName("Deve salvar Um professor")
    void deveSalvarUmProfessor() {
        ProfessorEntity professor = criarProfessorEntity();

        ProfessorEntity professorSalvo = this.repository.save(professor);

        assertThat(professorSalvo).isNotNull();
        assertThat(professorSalvo.getId()).isNotNull();
        assertThat(professorSalvo.getNome()).isEqualTo(professor.getNome());
        assertThat(professorSalvo.getCpf()).isEqualTo(professor.getCpf());
        assertThat(professorSalvo.getRg()).isEqualTo(professor.getRg());
        assertThat(professorSalvo.getEmail()).isEqualTo(professor.getEmail());
        assertThat(professorSalvo.getEndereco()).isEqualTo(professor.getEndereco());
    }

    @Test
    @DisplayName("Deve atualizar Um professor")
    void deveAtualizarUmProfessor() {
        ProfessorEntity professor = criarProfessorEntity();

        ProfessorEntity professorSalvo = this.repository.save(professor);

        professorSalvo.setNome("Professor Atualizado");

        ProfessorEntity professorAtualizado = this.repository.save(professorSalvo);

        assertThat(professorAtualizado).isNotNull();
        assertThat(professorAtualizado.getId()).isNotNull();
        assertThat(professorAtualizado.getNome()).isEqualTo(professorSalvo.getNome());
        assertThat(professorAtualizado.getCpf()).isEqualTo(professorSalvo.getCpf());
        assertThat(professorAtualizado.getRg()).isEqualTo(professorSalvo.getRg());
        assertThat(professorAtualizado.getEmail()).isEqualTo(professorSalvo.getEmail());
        assertThat(professorAtualizado.getEndereco()).isEqualTo(professorSalvo.getEndereco());
    }

    @Test
    @DisplayName("Deve deletar Um professor")
    void deveDeletarUmProfessor() {
        ProfessorEntity professor = criarProfessorEntity();

        ProfessorEntity professorSalvo = this.repository.save(professor);

        this.repository.delete(professorSalvo);
    }

    @Test
    @DisplayName("Deve recuperar Um professor")
    void deveRecuperarUmProfessor() {

    }

    @Test
    @DisplayName("Deve recuperar uma lista de professores")
    void deveRecuperarUmaListaDeProfessores() {

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
    ProfessorEntity criarProfessorEntityComId(){
        ProfessorEntity professorEntity = criarProfessorEntity();
        professorEntity.setId(1L);
        return professorEntity;
    }
}