package br.com.sportsfree.repository;

import br.com.sportsfree.entity.CursoEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalTime;
import java.util.Optional;

import static br.com.sportsfree.utils.CursoTesteUtils.criarCursoEntity;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@EnableJpaRepositories(repositoryBaseClass = AbstractRepositoryImpl.class)
@EntityScan(basePackageClasses = CursoEntity.class)
public class CursoRepositoryTest {
 
    @Autowired
    private CursoRepository repository;

    @Test
    @DisplayName("Deve salvar Um curso")
    void deveSalvarUmCurso() {
        CursoEntity curso = criarCursoEntity();

        CursoEntity cursoSalvo = this.repository.save(curso);

        assertThat(cursoSalvo).isNotNull();
        assertThat(cursoSalvo.getId()).isNotNull();
        assertThat(cursoSalvo.getDescricao()).isEqualTo(curso.getDescricao());
        assertThat(cursoSalvo.getProfessor()).isEqualTo(curso.getProfessor());
        assertThat(cursoSalvo.getEsporte()).isEqualTo(curso.getEsporte());
        assertThat(cursoSalvo.getLocal()).isEqualTo(curso.getLocal());
        assertThat(cursoSalvo.getDia()).isEqualTo(curso.getDia());
        assertThat(cursoSalvo.getHorarioInicio()).isEqualTo(curso.getHorarioInicio());
        assertThat(cursoSalvo.getHorarioFim()).isEqualTo(curso.getHorarioFim());
        assertThat(cursoSalvo.getQuantidadeMaxAlunos()).isEqualTo(curso.getQuantidadeMaxAlunos());

    }

    @Test
    @DisplayName("Deve atualizar Um curso")
    void deveAtualizarUmCurso() {
        CursoEntity curso = criarCursoEntity();

        CursoEntity cursoSalvo = this.repository.save(curso);

        cursoSalvo.setDescricao("Descrição atualizada");
        cursoSalvo.setDia("Sexta-Feira");
        cursoSalvo.setHorarioInicio(LocalTime.of(19,0));
        cursoSalvo.setHorarioFim(LocalTime.of(21,0));

        CursoEntity cursoAtualizado = this.repository.save(cursoSalvo);

        assertThat(cursoAtualizado).isNotNull();
        assertThat(cursoAtualizado.getId()).isNotNull();
        assertThat(cursoAtualizado.getDescricao()).isEqualTo(cursoSalvo.getDescricao());
        assertThat(cursoAtualizado.getProfessor()).isEqualTo(cursoSalvo.getProfessor());
        assertThat(cursoAtualizado.getEsporte()).isEqualTo(cursoSalvo.getEsporte());
        assertThat(cursoAtualizado.getLocal()).isEqualTo(cursoSalvo.getLocal());
        assertThat(cursoAtualizado.getDia()).isEqualTo(cursoSalvo.getDia());
        assertThat(cursoAtualizado.getHorarioInicio()).isEqualTo(cursoSalvo.getHorarioInicio());
        assertThat(cursoAtualizado.getHorarioFim()).isEqualTo(cursoSalvo.getHorarioFim());
        assertThat(cursoAtualizado.getQuantidadeMaxAlunos()).isEqualTo(cursoSalvo.getQuantidadeMaxAlunos());
    }

    @Test
    @DisplayName("Deve deletar Um curso")
    void deveDeletarUmCurso() {
        CursoEntity curso = criarCursoEntity();

        CursoEntity cursoSalvo = this.repository.save(curso);

        this.repository.delete(cursoSalvo);

        Optional<CursoEntity> optionalCurso = this.repository.findById(cursoSalvo.getId());
        assertThat(optionalCurso).isEmpty();
    }

    @Test
    @DisplayName("Deve recuperar Um curso")
    void deveRecuperarUmCurso() {
        CursoEntity curso = criarCursoEntity();
        CursoEntity cursoSalvo = this.repository.save(curso);
        Long id = cursoSalvo.getId();

        Optional<CursoEntity> optionalCurso = this.repository.findById(id);

        assertThat(optionalCurso)
                .isPresent()
                .contains(curso);
    }
    
}
