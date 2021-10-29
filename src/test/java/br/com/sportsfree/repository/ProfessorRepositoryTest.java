package br.com.sportsfree.repository;

import br.com.sportsfree.entity.ProfessorEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static br.com.sportsfree.utils.ProfessorTesteUtils.criarProfessorEntity;
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

        Optional<ProfessorEntity> optionalProfessor = this.repository.findById(professorSalvo.getId());
        assertThat(optionalProfessor).isEmpty();
    }

    @Test
    @DisplayName("Deve recuperar Um professor")
    void deveRecuperarUmProfessor() {
        ProfessorEntity professor = criarProfessorEntity();
        ProfessorEntity professorSalvo = this.repository.save(professor);
        Long id = professorSalvo.getId();

        Optional<ProfessorEntity> optionalProfessor = this.repository.findById(id);

        assertThat(optionalProfessor)
                .isPresent()
                .contains(professor);
    }

    @Test
    @DisplayName("Deve recuperar uma lista de professores")
    void deveRecuperarUmaListaDeProfessores() {
        ProfessorEntity professor = criarProfessorEntity();
        this.repository.save(professor);

        List<ProfessorEntity> professores = this.repository.findAll();

        assertThat(professores)
                .isNotEmpty()
                .contains(professor);
    }

}